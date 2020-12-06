package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class DayPlanFileRepositoryIntegrationTest {

    private val location = "/home/olga/my-cookbook-store"
    private val dayPlanRepository = DayPlanFileRepository(location)

    @Nested
    inner class FindDayPlansForDatesTest {

        @Test
        fun `should return found day plans`() = runBlocking {
            val dayPlan = dayPlanRepository.getDayPlanForDates(listOf(DateCalculator.toDate("2020-11-23")))
            Assertions.assertNotNull(dayPlan)
        }
    }
}
