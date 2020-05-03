package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.store.recipe.RecipeConverter
import java.io.File

class DayPlanConverter {

    private val FATS_PREFIX = "fats:"
    private val CARBS_PREFIX = "carbs:"
    private val DAY_PREFIX = "day:"
    private val KCAL_PREFIX = "kcal:"
    private val PROTEINS_PREFIX = "proteins:"

    val recipeConverter: RecipeConverter = RecipeConverter()

    fun getSummaryContent(dayPlan: DayPlan): String {
        var value = "$DAY_PREFIX${dayPlan.forDay}\n"
        value += "$KCAL_PREFIX${dayPlan.kcal}\n"
        value += "$PROTEINS_PREFIX${dayPlan.proteinPercentage}\n"
        value += "$CARBS_PREFIX${dayPlan.carbohydratesPercentage}\n"
        value += "$FATS_PREFIX${dayPlan.fatsPercentage}\n"
        return value
    }

    fun createDayplanFromFile(summaryFiles: List<File>, recipesFiles: List<File>): DayPlan {
        val recipes = recipeConverter.createRecipesFromFiles(recipesFiles)
        var forDay = ""
        var kcal = ""
        var proteins = 0
        var carbs = 0
        var fats = 0
        summaryFiles.get(0).forEachLine {
            if (it.contains(DAY_PREFIX)) forDay = it.replace(DAY_PREFIX, "")
            if (it.contains(KCAL_PREFIX)) kcal = it.replace(KCAL_PREFIX, "")
            if (it.contains(PROTEINS_PREFIX)) proteins = it.replace(PROTEINS_PREFIX, "").toInt()
            if (it.contains(CARBS_PREFIX)) carbs = it.replace(CARBS_PREFIX, "").toInt()
            if (it.contains(FATS_PREFIX)) fats = it.replace(FATS_PREFIX, "").toInt()

        }
        return DayPlan(recipes, forDay, kcal, proteins, carbs, fats)
    }


}
