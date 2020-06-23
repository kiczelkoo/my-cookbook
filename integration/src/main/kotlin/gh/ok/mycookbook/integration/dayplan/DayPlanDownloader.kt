package gh.ok.mycookbook.integration.dayplan

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import gh.ok.mycookbook.core.dayplan.IDayPlanRepository
import gh.ok.mycookbook.core.utils.DateCalculator
import org.openqa.selenium.chrome.ChromeDriver
import pl.mfcookbook.core.services.DayPlanLoginService
import pl.mfcookbook.core.services.DayPlanPageService
import java.time.LocalDate


class DayPlanDownloader(private val loginService: DayPlanLoginService,
                        private val dayPlanPageService: DayPlanPageService,
                        private val dayPlanRepository: IDayPlanRepository) : IDayPlanDownloader {

   override fun downloadDayPlansForDates(from: LocalDate, to: LocalDate) {
        val driver = ChromeDriver()
        loginService.login(driver)
        var start = from
        while (start.compareTo(to) <= 0) {
            val rawDayPlan = dayPlanPageService.getRawDayPLan(DateCalculator.toString(start), driver)
            if (rawDayPlan.isPresent) {
                dayPlanRepository.saveRaw(rawDayPlan.get(), start)
            }
            start = start.plusDays(1)
        }
        driver.close()
    }
}
