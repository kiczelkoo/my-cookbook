package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DayPlanRepositoryIntegrationTest {

    private val location = "/home/olga/my-cookbook-store"
    private val dayPlanRepository = DayPlanRepository(location)

    @Nested
    inner class FindDayPlansForDatesTest {

        @Test
        fun `should return found day plans`() {
            dayPlanRepository.getDayPlan(listOf(DateCalculator.toDate("2020-11-23")))
        }
    }
}
