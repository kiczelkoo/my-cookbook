package gh.ok.mycookbook.integration.recipe

import gh.ok.mycookbook.domain.groceries.product.Product
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import gh.ok.mycookbook.domain.recipe.entity.RecipeCategory
import gh.ok.mycookbook.integration.dayplan.DISH_SEPARATOR

class RecipeConverter {

    private val DESCRIPTION = "Sposób przygotowania:"
    private val MEAL_FOR_FEW_DAYS = "Przepis na"
    private val PIPE = "|"
    private val COLON = ":"

    fun toRecipes(mealsLines: List<String>): MutableList<Recipe> {
        val recipes = mutableListOf<Recipe>()
        mealsLines.forEach {
            recipes.add(createRecipeFromLines(it))
        }
        return recipes
    }

    private fun createRecipeFromLines(dishes: String): Recipe {
        val dishesLines = dishes.split(DISH_SEPARATOR)
        val summaryLine = dishesLines.first()
        val recipeName = dishesLines.get(1).split("\n").filter { filterMeaningfulLines(it) }.first()

        val allIngredients = mutableListOf<Ingredient>()
        val allDescriptions = mutableMapOf<String, List<String>>()

        dishesLines.subList(1, dishesLines.size - 1).forEach {
            val dish = it.split("\n").filter { filterMeaningfulLines(it) }
            if (dish.isNotEmpty()) {
                val descriptionStartIndx = getIndexOfLastIngredientLine(dish)
                allIngredients.addAll(mapIngredients(dish.subList(1, descriptionStartIndx)))
                allDescriptions.putAll(
                    mapOf(dish.first() to mapDescription(dish.subList(descriptionStartIndx, dish.size)))
                )
            }
        }
        return Recipe(
            extractCategory(summaryLine),
            recipeName,
            extractKcal(summaryLine),
            extractNutrience(summaryLine),
            extractPrepTime(summaryLine),
            allIngredients,
            allDescriptions)
    }

    private fun getIndexOfLastIngredientLine(dish: List<String>): Int {
        var descriptionStartIndx = -1
        dish.forEachIndexed { i, str ->
            if (str.contains(DESCRIPTION) || str.contains(MEAL_FOR_FEW_DAYS)) descriptionStartIndx =
                i
        }
        return if (descriptionStartIndx > 0) descriptionStartIndx else dish.size - 1
    }


    private fun mapDescription(preparation: List<String>): List<String> {
        val description = mutableListOf<String>()
        if (!preparation.isEmpty()) {
            preparation.forEachIndexed { count, line ->
                description.add("${count + 1}. $line")
            }
        }
        return description
    }

    private fun mapIngredients(ingredients: List<String>): List<Ingredient> {
        val ingredientsList = mutableListOf<Ingredient>()
        var amount = ""
        ingredients.forEachIndexed { index, line ->
            if (index % 2 == 0) {
                amount = amount + line
            } else {
                ingredientsList.add(Ingredient(Product(line), amount))
                amount = ""
            }
        }
        return ingredientsList
    }

    private fun filterMeaningfulLines(line: String) = line.isNotEmpty()
            && !line.toUpperCase().contains("WSPIERA")
            && !line.toUpperCase().contains("FOODS BY ANN")
            && !line.toUpperCase().contains("KUP")
            && !line.toUpperCase().contains("WYMIEŃ")
            && !line.toUpperCase().contains("PRZYPRAWY")


    private fun extractCategory(line: String) =
        mapToRecipeCategory(line.substring(0, line.indexOf(COLON)).trim())


    private fun extractKcal(line: String) =
        line.substring(line.indexOf(COLON), line.indexOf(PIPE)).replace(COLON, "")
            .replace("kcal", "").trim()


    private fun extractNutrience(line: String) =
        line.substring(line.indexOf(PIPE), line.lastIndexOf(PIPE)).replace(PIPE, "").trim()


    private fun extractPrepTime(line: String) =
        line.substring(line.lastIndexOf(PIPE)).replace("Czas przygotowania", "").replace(PIPE, "")
            .trim()


    private fun mapToRecipeCategory(value: String): RecipeCategory {
        if ("Śniadanie".equals(value)) return RecipeCategory.BREAKFAST
        if ("Przekąska poranna".equals(value)) return RecipeCategory.SNACK
        if ("Przekąska wieczorna".equals(value)) return RecipeCategory.SNACK
        if ("Przekąska przed treningiem".equals(value)) return RecipeCategory.TRAINING_SNACK
        if ("Przekąska po treningu".equals(value)) return RecipeCategory.TRAINING_SNACK
        if ("Obiad".equals(value)) return RecipeCategory.LUNCH
        if ("Kolacja".equals(value)) return RecipeCategory.DINNER else return RecipeCategory.OTHER
    }
}
