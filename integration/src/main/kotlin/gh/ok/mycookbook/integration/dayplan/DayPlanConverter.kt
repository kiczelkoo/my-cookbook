package gh.ok.mycookbook.integration.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.time.LocalDate

class DayPlanConverter {

    fun createDayPlan(recipes: List<Recipe>, dayPlanSummary: List<String>, day: LocalDate): DayPlan {
        var prots = 0
        var carbs = 0
        var fats = 0
        val kcal = if (dayPlanSummary.isEmpty() && dayPlanSummary.size < 4) "" else dayPlanSummary.get(0)
        dayPlanSummary.forEach {
            if (it.contains("Białka")) prots =  it.trim().substring(0, 2).toInt()
            if (it.contains("Węglowodany")) carbs = it.trim().substring(0, 2).toInt()
            if (it.contains("Tłuszcze")) fats = it.trim().substring(0, 2).toInt()
        }
        dayPlanSummary.forEach { }
        return DayPlan(recipes, DateCalculator.toString(day), kcal, prots, carbs, fats)
    }
}
