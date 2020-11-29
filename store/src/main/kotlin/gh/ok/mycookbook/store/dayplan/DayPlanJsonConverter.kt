package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.Meal
import gh.ok.mycookbook.domain.dayplan.MealCategory
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate

class DayPlanJsonConverter {

    fun convertToDayPlan(dayPlanJson: String): DayPlan {
        val dayPlan = JSONObject(dayPlanJson)
        return DayPlan(
            convertMeals(dayPlan.getJSONArray("meals")),
            getDate(dayPlan.getString("date")),
            dayPlan.getInt("kcal"),
            dayPlan.getInt("proteins"),
            dayPlan.getInt("carbs"),
            dayPlan.getInt("fats")
        )
    }

    private fun convertMeals(mealsJson: JSONArray): List<Meal> {
        for (i in 0..mealsJson.length()) {
            val mealJson = mealsJson.getJSONObject(i)
            val meal = Meal(
                mealJson.getInt("count"),
                MealCategory.valueOf(mealJson.getString("category")),
                mealJson.getInt("prepareTime"),
                convertRecipes(mealJson.getJSONArray("recipes")),
                mealJson.getInt("kcal"),
                mealJson.getInt("proteins"),
                mealJson.getInt("carbs"),
                mealJson.getInt("fats")
            )
        }
        return emptyList()
    }

    private fun getDate(dateString: String): LocalDate {
        return DateCalculator.toDate(dateString);
    }

    private fun convertRecipes(recipesJson: JSONArray): List<Recipe> {
        return emptyList();
    }
}
