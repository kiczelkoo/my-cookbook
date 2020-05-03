package gh.ok.mycookbook.store.recipe

import gh.ok.mycookbook.domain.groceries.product.Product
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import gh.ok.mycookbook.domain.recipe.entity.RecipeCategory
import java.io.File

class RecipeConverter {

    private val CATEGORY_KEY = "category:"
    private val KCAL_KEY = "kcal:"
    private val NUTRIENCE_KEY = "nutrience:"
    private val TIME_KEY = "prepTime:"
    private val RECIPE_KEY = "recipeName:"
    private val AMOUNT_SEPARATOR = "###"
    private val DESCRIPTION_KEY = "Description:"
    private val MEAL_KEY = "mealName:"

    fun createRecipeFileContent(recipe: Recipe): String {
        var value = ""
        value += createLine(CATEGORY_KEY, recipe.category)
        value += createLine(KCAL_KEY, recipe.kcal)
        value += createLine(NUTRIENCE_KEY, recipe.nutrience)
        value += createLine(TIME_KEY, recipe.prepTime)
        value += createLine(RECIPE_KEY, recipe.recipeName)
        recipe.ingredients.forEach {
            value += createLine("${it.product.name}$AMOUNT_SEPARATOR", it.amount)
        }
        value += createLine(DESCRIPTION_KEY, "")
        recipe.descriptions.forEach { name, description ->
            value += createLine(MEAL_KEY, name)
            description.forEach {
                value += createLine(name, it)
            }

        }
        return value
    }

    private fun createLine(key: String, value: Any) = "$key$value\n"

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
        val descriptions = mutableMapOf<String, List<String>>()

        val lines = file.useLines { it.toList() }
        lines.forEachIndexed { index, str ->
            if (str.contains(CATEGORY_KEY)) category = extractValue(str, CATEGORY_KEY)
            if (str.contains(KCAL_KEY)) kcal = extractValue(str, KCAL_KEY)
            if (str.contains(NUTRIENCE_KEY)) nutrience = extractValue(str, NUTRIENCE_KEY)
            if (str.contains(TIME_KEY)) prepTime = extractValue(str, TIME_KEY)
            if (str.contains(RECIPE_KEY)) recipeName = extractValue(str, RECIPE_KEY)
            if (str.contains(AMOUNT_SEPARATOR)) ingredients.add(extractIngredient(str))
            if (str.contains(DESCRIPTION_KEY)) indexOfDesc = index
        }
        val descriptionsLines = lines.subList(indexOfDesc, lines.size - 1)

        descriptionsLines.forEach {
            if (it.contains(MEAL_KEY)) {
                val mealName = extractValue(it, MEAL_KEY)
                descriptions.put(mealName, extractDescription(mealName, descriptionsLines))
            }
        }
        return Recipe(
            RecipeCategory.valueOf(category),
            recipeName,
            kcal,
            nutrience,
            prepTime,
            ingredients,
            descriptions
        )

    }

    private fun extractDescription(mealName: String, lines: List<String>) = lines
        .map {
            if (!it.contains(MEAL_KEY) && it.contains(mealName)) extractValue(it, mealName) else ""
        }
        .filter { it.isNotEmpty() }

    private fun extractIngredient(ingredientLine: String): Ingredient {
        val ingredientCompounds = ingredientLine.split(AMOUNT_SEPARATOR)
        return Ingredient(Product(ingredientCompounds.get(0)), ingredientCompounds.get(1))
    }

    private fun extractValue(line: String, key: String) = line.replace(key, "").trim()

}
