package gh.ok.mycookbook.backend.housekeeping

import gh.ok.mycookbook.core.diet.DayPlanService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class RecipeHousekeeper(private val dayPlanService: DayPlanService) {

    // TODO remove just for testing
    @Scheduled(fixedDelay = 50000)
    fun importRecipes() {
        dayPlanService.importOriginalDayPlans(LocalDate.of(2019, 12, 10), LocalDate.of(2019, 12, 12))
    }
}
