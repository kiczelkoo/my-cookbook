package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.store.recipe.RecipeConverter
import java.io.File

class DayPlanConverter {

    val recipeConverter: RecipeConverter = RecipeConverter()

    fun getSummaryContent(dayPlan: DayPlan): String {
        var value = "day:${dayPlan.forDay}\n"
        value += "kcal:${dayPlan.kcal}\n"
        value += "proteins:${dayPlan.proteinPercentage}\n"
        value += "carbs:${dayPlan.carbohydratesPercentage}\n"
        value += "fats:${dayPlan.fatsPercentage}\n"
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
            if (it.contains("day:")) forDay = it.replace("day:", "")
            if (it.contains("kcal:")) kcal = it.replace("kcal:", "")
            if (it.contains("proteins:")) proteins = it.replace("proteins:", "").toInt()
            if (it.contains("carbs:")) carbs = it.replace("carbs:", "").toInt()
            if (it.contains("fats:")) fats = it.replace("fats:", "").toInt()

        }
        return DayPlan(recipes, forDay, kcal, proteins, carbs, fats)
    }


}
