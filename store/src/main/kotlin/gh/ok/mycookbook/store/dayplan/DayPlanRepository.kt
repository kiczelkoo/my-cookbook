package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.diet.IDayPlanRepository
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import java.io.File

class DayPlanRepository(private val dayPlansLocation: String,
                        private val recipeRepository: IRecipeRepository) : IDayPlanRepository {

    override fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>) {
        dayPlans.forEach { dayPlan ->
            val dayPlanPath = "$dayPlansLocation/origin/${dayPlan.forDay}/"
            val file = File("$dayPlanPath/summary.txt")
                    .also { file -> file.parentFile.mkdirs() }
            file.writeText(dayPlan.getSummaryContent())
            recipeRepository.saveRecipes(dayPlanPath, dayPlan.meals)
        }
    }
}
