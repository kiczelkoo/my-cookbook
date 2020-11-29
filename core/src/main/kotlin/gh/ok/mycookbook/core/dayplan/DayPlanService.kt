package gh.ok.mycookbook.core.dayplan

import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.IDayPlanRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class DayPlanService(private val dayPlanRepository: IDayPlanRepository) {

    fun getDayPlans(fromDate: LocalDate, toDate: LocalDate): Flow<DayPlan> {
        val dates = prepareDatesForGivenRange(fromDate, toDate)
        return dayPlanRepository.getDayPlanForDates(dates)
    }

    private fun prepareDatesForGivenRange(fromDate: LocalDate, toDate: LocalDate): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var previous = fromDate
        while (previous.isBefore(toDate)) {
            dates.add(previous)
            previous = previous.plusDays(1)
        }
        dates.add(toDate)
        return dates
    }

}
