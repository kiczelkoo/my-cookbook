package gh.ok.mycookbook.core.diet

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import java.time.LocalDate

class DayPlanService(private val dayPlanDownloader: IDayPlanDownloader,
                     private val dayPlanRepository: IDayPlanRepository) {

    fun importOriginalDayPlans(fromDate: LocalDate, toDate: LocalDate): List<DayPlan> {
        val dayPlansToSave: List<DayPlan> = dayPlanDownloader.downloadDayPlansForDates(fromDate, toDate)
        dayPlanRepository.saveAllOriginalDayPlans(dayPlansToSave)
        return dayPlansToSave
    }

    fun getDayPlansForDates(fromDate: LocalDate, toDate: LocalDate): List<DayPlan> {
        val dates = prepareDatesForGivenRange(fromDate, toDate)
        val dayPlans = dayPlanRepository.findDayPlansForDates(dates)
        if (!dayPlans.isEmpty()) return dayPlans else return importOriginalDayPlans(fromDate, toDate)
    }

    private fun prepareDatesForGivenRange(fromDate: LocalDate, toDate: LocalDate): List<String> {
        val dates = mutableListOf<String>()
        val previous = fromDate
        while (previous.isBefore(toDate)) {
            dates.add(DateCalculator.toString(previous))
            previous.plusDays(1)
        }
        dates.add(DateCalculator.toString(toDate))
        return dates
    }
}
