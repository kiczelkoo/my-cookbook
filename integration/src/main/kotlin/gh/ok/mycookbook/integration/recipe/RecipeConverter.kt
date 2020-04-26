package gh.ok.mycookbook.integration.recipe

import gh.ok.mycookbook.domain.groceries.product.Product
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import gh.ok.mycookbook.domain.recipe.entity.RecipeCategory

class RecipeConverter {

    fun toRecipes(meals: List<String>): MutableList<Recipe> {
        val recipes = mutableListOf<Recipe>()
        meals.forEach {
            val dishes = it.split("DISH")

            val allIngredients = mutableListOf<Ingredient>()
            val allDescriptions = mutableMapOf<String, List<String>>()

            var recipeName = ""

            dishes.forEachIndexed { index, dish ->
                if (index == 1) {
                    recipeName = extractRecipeName(dish)
                }
                if (index > 0) {
                    allIngredients.addAll(extractIngredients(dish))
                    allDescriptions.putAll(extractDescriptions(dish))
                }
            }

            recipes.add(Recipe(
                    extractCategory(dishes.first()),
                    recipeName,
                    extractKcal(dishes.first()),
                    extractNutrience(dishes.first()),
                    extractPrepTime(dishes.first()),
                    allIngredients,
                    allDescriptions
            ))
        }
        return recipes
    }

    private fun extractRecipeName(dish: String): String {
        return dish.split("\n").filter { filterMeaningfulLines(it) }.first()

    }

    private fun extractDescriptions(dish: String): Map<String, List<String>> {
        val dishLines: List<String> = dish.split("\n").filter { filterMeaningfulLines(it) }
        if (!dishLines.isEmpty()) {
            val descIndx = dishLines.indexOf("Sposób przygotowania:")
            val numOfLines = dishLines.size
            val ingrEndIndx = if (descIndx > 0 && descIndx <= numOfLines) descIndx else numOfLines
            val descStartIndx = if (ingrEndIndx + 1 < numOfLines) ingrEndIndx + 1 else numOfLines
            val preparation = dishLines.subList(descStartIndx, numOfLines)
            return mapOf(dishLines.first() to mapDescription(preparation))
        }
        return emptyMap()
    }

    private fun extractIngredients(dish: String): List<Ingredient> {
        val dishLines: List<String> = dish.split("\n").filter { filterMeaningfulLines(it) }
        if (!dishLines.isEmpty()) {
            val descIndx = dishLines.indexOf("Sposób przygotowania:")
            val numOfLines = dishLines.size
            val ingrEndIndx = if (descIndx > 0 && descIndx <= numOfLines) descIndx else numOfLines
            val ingredients = dishLines.subList(1, ingrEndIndx)
            return mapIngredients(ingredients)
        }
        return emptyList()
    }

    private fun mapDescription(preparation: List<String>): MutableList<String> {
        val description = mutableListOf<String>()
        if (!preparation.isEmpty()) {
            preparation.forEachIndexed { count, line ->
                description.add("${count + 1}. $line")
            }
        }
        return description
    }

    private fun mapIngredients(ingredients: List<String>): MutableList<Ingredient> {
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


    private fun extractCategory(line: String): RecipeCategory {
        return mapToRecipeCategory(line.substring(0, line.indexOf(":")).trim())
    }

    private fun extractKcal(line: String): String {
        return line.substring(line.indexOf(":"), line.indexOf("|")).replace(":", "").trim()
    }

    private fun extractNutrience(line: String): String {
        return line.substring(line.indexOf("|"), line.lastIndexOf("|")).replace("|", "").trim()
    }

    private fun extractPrepTime(line: String): String {
        return line.substring(line.lastIndexOf("|")).replace("Czas przygotowania", "").replace("|", "").trim()
    }

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
