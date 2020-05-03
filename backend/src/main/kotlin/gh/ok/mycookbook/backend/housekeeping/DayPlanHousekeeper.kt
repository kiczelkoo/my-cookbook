package gh.ok.mycookbook.backend.housekeeping

import gh.ok.mycookbook.core.diet.DayPlanService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DayPlanHousekeeper(private val dayPlanService: DayPlanService) {

    // TODO remove just for testing
    @Scheduled(fixedDelay = 86400000)
    fun importAllDayPlans() {
        val startDay = LocalDate.of(2016, 7, 12)
        val endDay = LocalDate.of(2020, 5, 3)
        var from = startDay
        while (from.plusDays(7).isBefore(endDay)) {
            dayPlanService.importOriginalDayPlans(from, from.plusDays(7))
            from = from.plusDays(7)

        }
        if (from.plusDays(7).isAfter(endDay)) {
            dayPlanService.importOriginalDayPlans(from, endDay)
        }
    }

}
