package pl.mfcookbook.core.services

import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.LocalDate
import java.util.regex.Pattern
import java.util.stream.Collectors

class DayPlanPageService {
    private val DAY_PLAN_BASE_URL = "https://dieta.hpba.pl/day-plan/"
    private val MEALS_SELECTOR = "section.meal"
    private val DISH_SELECTOR = "div.row"
    private val MEAL_SUMMARY_SELECTOR = "h2"
    private val DAY_PLAN_SUMMARY_SELECTOR = "section.summary div div div div h3"
    private val KCAL = "div.odometer.odometer-auto-theme"
    private val KCAL_PATTERN = Pattern.compile("[\\d\n]{3}\\d")
    private val KCAL_VALUES_SELECTOR = "span.odometer-value"

    fun getAllMealsForADay(driver: ChromeDriver, date: LocalDate): List<String> {
        val allMeals = mutableListOf<String>()
        val wait = WebDriverWait(driver, 10)
        try {
            driver.get(DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date)))
            wait.until(ExpectedConditions.textMatches(By.cssSelector(KCAL), KCAL_PATTERN))
            if (!isProperPageOpened(driver, date)) {
                println("not correct url ${driver.currentUrl} ${DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date))}")
                return emptyList()
            }
            driver.findElementsByCssSelector(MEALS_SELECTOR).forEach {
                allMeals.add(getMeal(it))
            }
            return allMeals
        } catch (exc: Exception) {
            println("Exception while downloading meals for date: $date")
            exc.printStackTrace()
            return emptyList()
        }
    }

    private fun isProperPageOpened(driver: ChromeDriver, date: LocalDate)
            = driver.currentUrl.contains(DateCalculator.toString(date))

    fun getDayPlanSummary(driver: ChromeDriver, date: LocalDate): List<String> {
        val summary = mutableListOf<String>()
        val wait = WebDriverWait(driver, 10)
        try {
            driver.get(DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date)))
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(KCAL_VALUES_SELECTOR), 4))
            if (!isProperPageOpened(driver, date)) {
                println("not correct url ${driver.currentUrl} ${DAY_PLAN_BASE_URL.plus(DateCalculator.toString(date))}")
                return emptyList()
            }
            summary.add(driver.findElementsByCssSelector(KCAL_VALUES_SELECTOR)
                    .map { it.text }.stream().collect(Collectors.joining()))
            summary.addAll(driver.findElementsByCssSelector(DAY_PLAN_SUMMARY_SELECTOR).map { it.text })
            return summary
        } catch (exc: Exception) {
            println("Exception while downloading day plan summary for date: $date")
            exc.printStackTrace()
            return emptyList()
        }
    }

    fun getMeal(mealElement: WebElement): String {
        var meal = mealElement.findElements(By.cssSelector(MEAL_SUMMARY_SELECTOR)).first().text + "\n"
        mealElement.findElements(By.cssSelector(DISH_SELECTOR)).forEach {
            meal += "DISH\n"
            meal += it.text
        }
        return meal
    }

}
