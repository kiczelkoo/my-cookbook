package gh.ok.mycookbook.store.recipe

import gh.ok.mycookbook.domain.groceries.product.Product
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import gh.ok.mycookbook.domain.recipe.entity.RecipeCategory
import java.io.File

class RecipeConverter {

    fun createRecipeFileContent(recipe: Recipe): String {
        var value = ""
        value += "category:${recipe.category}"
        value += "kcal:${recipe.kcal}\n"
        value += "nutrience:${recipe.nutrience}\n"
        value += "prepTime:${recipe.prepTime}\n"
        value += "recipeName:${recipe.recipeName}\n"
        recipe.ingredients.forEach {
            value += "${it.product.name}###${it.amount}\n"
        }
        value += "Description:\n"
        recipe.descriptions.forEach { name, description ->
            value += "name: $name\n"
            description.forEach {
                value += "$name$$$$it\n"
            }

        }
        return value
    }

    fun createRecipesFromFiles(recipesFiles: List<File>): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        recipesFiles.forEach { recipes.add(createRecipe(it)) }
        return recipes
    }

    private fun createRecipe(file: File): Recipe {
        var category = ""
        var kcal = ""
        var nutrience = ""
        var prepTime = ""
        var recipeName = ""
        var indexOfDesc = 0
        val ingredients = mutableListOf<Ingredient>()
        val descriptions = mutableMapOf<String, MutableList<String>>()

        val lines = file.useLines { it.toList() }
        lines.forEachIndexed { index, str ->
            if (str.contains("category:")) category = str.replace("category:", "")
            if (str.contains("kcal:")) kcal = str.replace("kcal:", "")
            if (str.contains("nutrience:")) nutrience = str.replace("nutrience:", "")
            if (str.contains("prepTime:")) prepTime = str.replace("prepTime:", "")
            if (str.contains("recipeName:")) recipeName = str.replace("recipeName:", "")
            if (str.contains("###")) ingredients.add(createIngredient(str))
            if (str.contains("Description:")) indexOfDesc = index
        }
        lines.subList(indexOfDesc, lines.size - 1).forEach {
            var name = ""
            if (it.contains("name:")) {
                name = it.replace("name:", "")
                descriptions.put(name, mutableListOf())
            }
            if (it.contains("$$$")) addToDescription(descriptions, it)
        }
        return Recipe(RecipeCategory.valueOf(category),
                recipeName,
                kcal,
                nutrience,
                prepTime,
                ingredients,
                descriptions)

    }

    private fun addToDescription(descriptions: MutableMap<String, MutableList<String>>, line: String) {
        val descriptionLine = line.split("$$$")
        descriptions.get(descriptionLine.get(0))?.add(descriptionLine.get(1))
    }


    private fun createIngredient(ingredientLine: String): Ingredient {
        val ingredientCompounds = ingredientLine.split("###")
        return Ingredient(Product(ingredientCompounds.get(0)), ingredientCompounds.get(1))
    }

}
