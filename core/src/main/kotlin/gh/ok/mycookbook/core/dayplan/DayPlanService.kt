package gh.ok.mycookbook.core.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class DayPlanService(private val dayPlanRepository: IDayPlanRepository) {

    fun getDayPlans(fromDate: LocalDate, toDate: LocalDate): Flow<DayPlan> {
        val dates = prepareDatesForGivenRange(fromDate, toDate)
        return dayPlanRepository.findDayPlans(dates)
    }

    private fun prepareDatesForGivenRange(fromDate: LocalDate, toDate: LocalDate): List<String> {
        val dates = mutableListOf<String>()
        var previous = fromDate
        while (previous.isBefore(toDate)) {
            println(DateCalculator.toString(previous))
            dates.add(DateCalculator.toString(previous))
            previous = previous.plusDays(1)
        }
        dates.add(DateCalculator.toString(toDate))
        return dates
    }

}
