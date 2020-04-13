package gh.ok.mycookbook.integration.recipe

import gh.ok.mycookbook.core.recipe.IRecipeDownloader
import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.chrome.ChromeDriver
import pl.mfcookbook.core.services.RecipeDayPlanService
import pl.mfcookbook.core.services.RecipeLoginService
import java.time.LocalDate


class RecipeDownloader(private val loginService: RecipeLoginService,
                       private val dayPlanService: RecipeDayPlanService) : IRecipeDownloader {

    override fun downloadRecipesForDates(from: LocalDate, to: LocalDate): Map<String, List<String>> {
        val driver = ChromeDriver()
        loginService.login(driver)
        var start = from
        val allMeals: MutableMap<String, List<String>> = mutableMapOf()
        while (start.compareTo(to) <= 0) {
            try {
                val meals: List<String> = dayPlanService.getAllMealsForADay(driver, start)
                allMeals.put(DateCalculator.toString(start), meals)
            } catch (exc: Exception) {
                println("exc for day: $start: ${exc.message}")
            }
            start = start.plusDays(1)
        }
        driver.close()
        return allMeals
    }
}
