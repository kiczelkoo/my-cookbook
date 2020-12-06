package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.Meal
import gh.ok.mycookbook.domain.dayplan.MealCategory
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import gh.ok.mycookbook.domain.recipe.entity.IngredientUnit
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate

class DayPlanJsonConverter {

    fun convertToDayPlan(dayPlanJson: String): DayPlan {
        val dayPlan = JSONObject(dayPlanJson)
        return DayPlan(
            convertMeals(dayPlan),
            getDate(dayPlan.getString("date")),
            dayPlan.getInt("kcal"),
            dayPlan.getInt("proteins"),
            dayPlan.getInt("carbs"),
            dayPlan.getInt("fats")
        )
    }

    private fun convertMeals(dayPlan: JSONObject): List<Meal> {
        val mealsJson = dayPlan.getJSONArray("meals")
        val meals = ArrayList<Meal>()
        for (i in 0 until mealsJson.length()) {
            val mealJson = mealsJson.getJSONObject(i)
            meals.add(
                Meal(
                    mealJson.getInt("count"),
                    MealCategory.valueOf(mealJson.getString("category")),
                    mealJson.getInt("prepareTime"),
                    convertRecipes(mealJson),
                    mealJson.getInt("kcal"),
                    mealJson.getInt("proteins"),
                    mealJson.getInt("carbs"),
                    mealJson.getInt("fats")
                )
            )
        }
        return meals
    }

    private fun getDate(dateString: String): LocalDate {
        return DateCalculator.toDate(dateString);
    }

    private fun convertRecipes(mealJson: JSONObject): List<Recipe> {
        val recipesJson: JSONArray = mealJson.getJSONArray("recipes")
        val recipes = ArrayList<Recipe>()
        for (i in 0 until recipesJson.length()) {
            val recipeJson = recipesJson.getJSONObject(i)
            recipes.add(
                Recipe(
                    recipeJson.getString("title"),
                    convertToIngredients(recipeJson),
                    convertToDescription(recipeJson)
                )
            )
        }
        return recipes
    }

    private fun convertToDescription(recipeJson: JSONObject): Map<Int, String> {
        return if (recipeJson.has("description")) {
            val descriptionJson = recipeJson.getJSONObject("description")
            val description = HashMap<Int, String>()
            for (i in 1..descriptionJson.length()) {
                description.put(i, descriptionJson.getString((i).toString()))
            }
            description
        } else emptyMap()
    }

    private fun convertToIngredients(recipeJson: JSONObject): List<Ingredient> {
        val ingredientsJson = recipeJson.getJSONArray("ingredients")
        val ingredients = ArrayList<Ingredient>()
        for (i in 0 until ingredientsJson.length()) {
            val ingredientJson = ingredientsJson.getJSONObject(i)
            ingredients.add(Ingredient(ingredientJson.getString("name"), convertToAmounts(ingredientJson)))
        }
        return ingredients
    }

    private fun convertToAmounts(ingredientJson: JSONObject): List<Pair<Double, IngredientUnit>> {
        val amountsJson = ingredientJson.getJSONArray("amounts")
        val amounts = ArrayList<Pair<Double, IngredientUnit>>()
        for (i in 0 until amountsJson.length()) {
            val amountJson = amountsJson.getJSONObject(i)
            amounts.add(Pair(amountJson.getDouble("amount"), IngredientUnit.fromString(amountJson.getString("unit"))))
        }
        return amounts
    }
}
