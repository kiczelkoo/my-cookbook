package pl.mfcookbook.core.services

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*
import java.util.regex.Pattern

class DayPlanPageService {
    private val DAY_PLAN_BASE_URL = "https://dieta.hpba.pl/day-plan/"
    private val KCAL = "div.odometer.odometer-auto-theme"
    private val KCAL_PATTERN = Pattern.compile("[\\d\n]{3}\\d")

    fun getRawDayPLan(date: String, driver: ChromeDriver): Optional<String> {
        val wait = WebDriverWait(driver, 10)
        try {
            driver.get(DAY_PLAN_BASE_URL.plus(date))
            wait.until(ExpectedConditions.textMatches(By.cssSelector(KCAL), KCAL_PATTERN))
            if (!isProperPageOpened(driver, date)) {
                println("not correct url ${driver.currentUrl} ${DAY_PLAN_BASE_URL.plus(date)}")
                return Optional.empty()
            }
            return Optional.of(driver.pageSource)
        } catch (exc: Exception) {
            println("Exception while downloading meals for date: $date")
            exc.printStackTrace()
            return Optional.empty()
        }
    }

    private fun isProperPageOpened(driver: ChromeDriver, date: String) =
        driver.currentUrl.contains(date)

    // todo just temporary code - new concept oF download data
//    private fun saveRawHtml(driver: ChromeDriver, date: LocalDate) {
//        val file = File("/home/olga/Documents/przepisy/hpba-diet/${DateCalculator.toString(date)}.html")
//            .also { file -> file.parentFile.mkdirs() }
//        file.writeText(driver.pageSource)
//    }

}
