package pl.mfcookbook.core.services

import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.LocalDate
import java.util.regex.Pattern

class RecipeDayPlanService {
    private val DAY_PLAN_BASE_URL = "https://dieta.hpba.pl/day-plan/"
    private val MEALS_SELECTOR = "section.meal"
    private val DISH_SELECTOR = "div.row"
    private val MEAL_SUMMARY_SELECTOR = "h2"
    private val KCAL = "div.odometer-inside"

    fun getAllMealsForADay(driver: ChromeDriver, date: LocalDate): List<String> {
        val allMeals = mutableListOf<String>()
        val wait = WebDriverWait(driver, 5)
        driver.get(DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date)))
        wait.until(ExpectedConditions.textMatches(By.cssSelector(KCAL), Pattern.compile("[\\d\n]{3}\\d")))
        driver.findElementsByCssSelector(MEALS_SELECTOR).forEach {
            var meal = ""
            val summary = it.findElements(By.cssSelector(MEAL_SUMMARY_SELECTOR)).first().text
            meal += summary + "\n"
            it.findElements(By.cssSelector(DISH_SELECTOR)).forEach {
                meal += "DISH\n"
                meal += it.text
            }
            println(meal)
            allMeals.add(meal)
        }
        return allMeals
    }

}
