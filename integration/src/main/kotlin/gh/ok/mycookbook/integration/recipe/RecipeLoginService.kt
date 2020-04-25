package pl.mfcookbook.core.services

import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

class RecipeLoginService(val userName: String, val password: String) {
    private val LOGIN_PAGE_URL = "https://dieta.hpba.pl/auth/login"
    private val EMAIL_INPUT_NAME = "email"
    private val PSWD_INPUT_NAME = "password"
    private val LOGIN_BUTTON_CSS_SELECTOR = "button.btn.btn-blue"

    fun login(driver: ChromeDriver) {
        driver.get(LOGIN_PAGE_URL)
        val wait = WebDriverWait(driver, 1)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(EMAIL_INPUT_NAME))).sendKeys(userName)
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(PSWD_INPUT_NAME))).sendKeys(password)
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(LOGIN_BUTTON_CSS_SELECTOR))).click()
        val start = System.currentTimeMillis()
        while (driver.currentUrl != "https://app.dieta.hpba.pl/day-plan" && shouldWait(start, 1)) {
            println("wait")
        }

    }

    private fun shouldWait(start: Long, waitTime: Int): Boolean = System.currentTimeMillis() - start < (waitTime * 1000)
}
