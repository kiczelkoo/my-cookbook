package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.dayplan.IDayPlanRepository
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.store.recipe.RecipeRepository
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DayPlanRepositoryIntegrationTest {

    private val location = "/home/olga/Documents/przepisy/hpba-diet";
    private val recipeRepository: IRecipeRepository = RecipeRepository(location)

    private val dayPlanRepository: IDayPlanRepository = DayPlanRepository(location, recipeRepository)

    @Nested
    inner class FindDayPlansForDatesTest {

        @Test
        fun `should return found day plans`() {
            dayPlanRepository.findDayPlans(listOf("2019-12-10", "2019-12-11", "2019-12-12"))
        }
    }
}
