package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.diet.IDayPlanRepository
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.store.CUSTOM_PLAN_LOCATION
import gh.ok.mycookbook.store.DAY_PLAN_SUMMARY_FILE
import gh.ok.mycookbook.store.ORIGINAL_PLAN_LOCATION
import kotlinx.coroutines.flow.flow
import java.io.File

class DayPlanRepository(
    private val dayPlansLocation: String,
    private val recipeRepository: IRecipeRepository
) : IDayPlanRepository {

    val dayPlanConverter = DayPlanConverter()

    override fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>) {
        dayPlans.forEach { dayPlan ->
            val dayPlanPath = "$dayPlansLocation$ORIGINAL_PLAN_LOCATION/${dayPlan.forDay}/"
            val file = File("$dayPlanPath/$DAY_PLAN_SUMMARY_FILE")
                .also { file -> file.parentFile.mkdirs() }
            file.writeText(dayPlanConverter.getSummaryContent(dayPlan))
            recipeRepository.saveRecipes(dayPlanPath, dayPlan.meals)
        }
    }

    override fun findDayPlans(dates: List<String>) = flow {
        dates.forEach { date ->
            val files: Pair<List<File>, List<File>> =
                readListOfFile(date).partition { it.name.equals(DAY_PLAN_SUMMARY_FILE) }
            emit(dayPlanConverter.createDayplanFromFile(files.first, files.second))
        }
    }

    private fun readListOfFile(date: String): List<File> {
        var foundFiles = File("$dayPlansLocation$CUSTOM_PLAN_LOCATION/$date").listFiles()
        if (foundFiles == null || foundFiles.isEmpty()) {
            foundFiles = File("$dayPlansLocation$ORIGINAL_PLAN_LOCATION/$date").listFiles()
        }
        if (foundFiles == null || foundFiles.isEmpty()) return emptyList() else return foundFiles.asList()
    }
}
