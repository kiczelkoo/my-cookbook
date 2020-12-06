package gh.ok.mycookbook.application.housekeeping

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DayPlanHousekeeper(private val dayPlanDownloader: IDayPlanDownloader) {

//    @Scheduled(fixedDelay = 86400000)
//    fun downloadDayPlans() {
//        // TODO other way to calculate dates
//        val startDay = LocalDate.of(2016, 7, 12)
//        val endDay = LocalDate.of(2020, 5, 3)
//        var from = startDay
//        while (from.plusDays(7).isBefore(endDay)) {
//            dayPlanDownloader.downloadDayPlansForDates(from, from.plusDays(7))
//            importDayPlans(from, from.plusDays(7))
//            from = from.plusDays(7)
//        }
//        if (from.plusDays(7).isAfter(endDay)) {
//            dayPlanDownloader.downloadDayPlansForDates(from, endDay)
//        }
//    }

//    @Scheduled(fixedDelay = 86400000)
    fun downloadDayPlans() {
     dayPlanDownloader.convertToDayPlan()
    }


    @Async
    private fun importDayPlans(from: LocalDate, plusDays: LocalDate) {
        println("import new day plans")
        // TODO implement
    }

}
