package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.diet.IDayPlanRepository
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import java.io.File

class DayPlanRepository(private val dayPlansLocation: String,
                        private val recipeRepository: IRecipeRepository) : IDayPlanRepository {

    private val ORIGINAL_PLAN_LOCATION = "/origin"
    private val CUSTOM_PLAN_LOCATION = "/my-plans"

    override fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>) {
        dayPlans.forEach { dayPlan ->
            val dayPlanPath = "$dayPlansLocation$ORIGINAL_PLAN_LOCATION/${dayPlan.forDay}/"
            val file = File("$dayPlanPath/summary.txt")
                    .also { file -> file.parentFile.mkdirs() }
            file.writeText(dayPlan.getSummaryContent())
            recipeRepository.saveRecipes(dayPlanPath, dayPlan.meals)
        }
    }

    override fun findDayPlansForDates(dates: List<String>): List<DayPlan> {
        dates.forEach { date ->
            val files: List<File> = readListOfFile(date)
            files.forEach {
                if (it.name.equals("summary.txt")) createSummaryForDayPlan(it) else createRecipe(it)

            }

        }
        return emptyList()
    }

    private fun createRecipe(it: File) {

    }

    private fun createSummaryForDayPlan(it: File) {
    }

    private fun readListOfFile(date: String): List<File> {
        var foundFiles = File("$dayPlansLocation$CUSTOM_PLAN_LOCATION/$date").listFiles()
        if (foundFiles == null || foundFiles.isEmpty()) {
            foundFiles = File("$dayPlansLocation$ORIGINAL_PLAN_LOCATION/$date").listFiles()
        }
        if (foundFiles == null || foundFiles.isEmpty()) return emptyList() else return foundFiles.asList()
    }
}
