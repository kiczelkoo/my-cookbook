package pl.mfcookbook.core.services

import org.openqa.selenium.chrome.ChromeDriver

class RecipeLoginService(val userName: String, val password: String) {
    private val LOGIN_PAGE_URL = "https://dieta.hpba.pl/auth/login"
    private val EMAIL_INPUT_NAME = "email"
    private val PSWD_INPUT_NAME = "password"
    private val LOGIN_BUTTON_CSS_SELECTOR = "button.btn.btn-blue"

    fun login(driver: ChromeDriver) {
        driver.get(LOGIN_PAGE_URL)
        driver.findElementByName(EMAIL_INPUT_NAME).sendKeys(userName)
        driver.findElementByName(PSWD_INPUT_NAME).sendKeys(password)
        driver.findElementByCssSelector(LOGIN_BUTTON_CSS_SELECTOR).click()
        Thread.sleep(1000)
    }
}
