package pl.mfcookbook.core.services

import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.time.LocalDate

class RecipeDayPlanService {
    private val DAY_PLAN_BASE_URL = "https://dieta.hpba.pl/day-plan/"
    private val MEALS_SELECTOR = "section.meal"
    private val DISH_SELECTOR = "div.row"
    private val MEAL_SUMMARY_SELECTOR = "h2"

    fun getAllMealsForADay(driver: ChromeDriver, date: LocalDate): List<String> {
        val allMeals = mutableListOf<String>()
        driver.get(DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date)))
        Thread.sleep(5000)
        driver.findElementsByCssSelector(MEALS_SELECTOR).forEach {
            var meal = ""
            val summary = it.findElements(By.cssSelector(MEAL_SUMMARY_SELECTOR)).first().text
            meal += summary + "\n"
            it.findElements(By.cssSelector(DISH_SELECTOR)).forEach {
                meal += "DISH\n"
                meal += it.text
            }
            allMeals.add(meal)
        }
        return allMeals
    }

}
