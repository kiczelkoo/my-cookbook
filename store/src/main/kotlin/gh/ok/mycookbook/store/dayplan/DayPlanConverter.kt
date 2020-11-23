package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.store.recipe.RecipeConverter
import java.io.File
import java.util.*

class DayPlanConverter {

    private val FATS_PREFIX = "fats:"
    private val CARBS_PREFIX = "carbs:"
    private val DAY_PREFIX = "day:"
    private val KCAL_PREFIX = "kcal:"
    private val PROTEINS_PREFIX = "proteins:"

    val recipeConverter: RecipeConverter = RecipeConverter()

    fun getSummaryContent(dayPlan: DayPlan): String {
        var value = "$DAY_PREFIX${dayPlan.date}\n"
        value += "$KCAL_PREFIX${dayPlan.kcal}\n"
        value += "$PROTEINS_PREFIX${dayPlan.proteins}\n"
        value += "$CARBS_PREFIX${dayPlan.carbs}\n"
        value += "$FATS_PREFIX${dayPlan.fats}\n"
        return value
    }

    fun createDayplanFromFile(summaryFiles: List<File>, recipesFiles: List<File>): DayPlan {
        val recipes = recipeConverter.createRecipesFromFiles(recipesFiles)
        var date = Date()
        var kcal = 0
        var proteins = 0
        var carbs = 0
        var fats = 0
        summaryFiles.get(0).forEachLine {
            if (it.contains(PROTEINS_PREFIX)) proteins = it.replace(PROTEINS_PREFIX, "").toInt()
            if (it.contains(CARBS_PREFIX)) carbs = it.replace(CARBS_PREFIX, "").toInt()
            if (it.contains(FATS_PREFIX)) fats = it.replace(FATS_PREFIX, "").toInt()

        }
        return DayPlan(recipes, date, kcal, proteins, carbs, fats)
    }


}
