package gh.ok.mycookbook.backend.housekeeping

import gh.ok.mycookbook.core.diet.DayPlanService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class RecipeHousekeeper(private val dayPlanService: DayPlanService) {

    // TODO remove just for testing
    @Scheduled(fixedDelay = 86400000)
    fun importRecipes() {
        val startDay = LocalDate.of(2016, 7, 12)
        val endDay = LocalDate.of(2016, 4, 26)
        var current = startDay
        while (current.isBefore(endDay)) {
            val until = current.plusDays(7)
            dayPlanService.importOriginalDayPlans(current, until)
        }
        if (current.isAfter(endDay)) {
            dayPlanService.importOriginalDayPlans(current, endDay)
        }
    }
}
