package pl.mfcookbook.core.services

import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.chrome.ChromeDriver
import java.time.LocalDate

class RecipeDayPlanService {
    private val DAY_PLAN_BASE_URL = "https://dieta.hpba.pl/day-plan/"
    private val MEALS_SELECTOR = "section.meal"

    fun getAllMealsForADay(driver: ChromeDriver, date: LocalDate): List<String> {
        driver.get(DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date)))
        Thread.sleep(5000)
        return driver.findElementsByCssSelector(MEALS_SELECTOR).map { el -> el.text }
    }

}
