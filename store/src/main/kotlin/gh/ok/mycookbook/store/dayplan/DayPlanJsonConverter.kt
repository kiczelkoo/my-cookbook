package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.Meal
import gh.ok.mycookbook.domain.dayplan.MealCategory
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
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
        for (i in 0..mealsJson.length() - 1) {
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
        for (i in 0..recipesJson.length() - 1) {
            val recipeJson = recipesJson.getJSONObject(i)
            recipes.add(
                Recipe(
                    recipeJson.getString("title"),
                    convertIngredients(recipeJson),
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

    private fun convertIngredients(recipeJson: JSONObject): List<Ingredient> {
        val ingredientsJson = recipeJson.getJSONArray("ingredients")
//        TODO("Not yet implemented")
        return emptyList()
    }
}
