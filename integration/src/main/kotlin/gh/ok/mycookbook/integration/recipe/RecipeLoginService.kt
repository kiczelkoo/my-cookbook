package pl.mfcookbook.core.services

import org.openqa.selenium.chrome.ChromeDriver

class RecipeLoginService(val userName: String, val password: String) {
    companion object {
        const val LOGIN_PAGE_URL = "https://dieta.hpba.pl/auth/login"
        const val EMAIL_INPUT_NAME = "email"
        const val PSWD_INPUT_NAME = "password"
        const val LOGIN_BUTTON_CSS_SELECTOR = "button.btn.btn-blue"
    }

    fun login(driver: ChromeDriver) {
        driver.get(LOGIN_PAGE_URL)
        driver.findElementByName(EMAIL_INPUT_NAME).sendKeys(userName)
        driver.findElementByName(PSWD_INPUT_NAME).sendKeys(password)
        driver.findElementByCssSelector(LOGIN_BUTTON_CSS_SELECTOR).click()
        Thread.sleep(1000)
    }
}
