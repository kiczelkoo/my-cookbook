package gh.ok.mycookbook.integration.dayplan

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.integration.recipe.RecipeConverter
import org.openqa.selenium.chrome.ChromeDriver
import pl.mfcookbook.core.services.DayPlanPageService
import pl.mfcookbook.core.services.DayPlanLoginService
import java.time.LocalDate


class DayPlanDownloader(private val loginService: DayPlanLoginService,
                        private val dayPlanService: DayPlanPageService) : IDayPlanDownloader {

    private val recipeConverter = RecipeConverter()
    private val dayPlanConverter = DayPlanConverter()

    override fun downloadDayPlansForDates(from: LocalDate, to: LocalDate): List<DayPlan> {
        val driver = ChromeDriver()
        loginService.login(driver)
        var start = from
        val dayPlans = mutableListOf<DayPlan>()
        while (start.compareTo(to) <= 0) {
            val meals: List<String> = dayPlanService.getAllMealsForADay(driver, start)
            val recipes = recipeConverter.toRecipes(meals)
            val dayPlanSummary = dayPlanService.getDayPlanSummary(driver, start)
            dayPlans.add(dayPlanConverter.createDayPlan(recipes, dayPlanSummary, start))
            start = start.plusDays(1)
        }
        driver.close()
        return dayPlans
    }
}
