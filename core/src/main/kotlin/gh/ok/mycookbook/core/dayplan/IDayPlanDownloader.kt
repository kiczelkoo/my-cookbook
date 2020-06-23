package gh.ok.mycookbook.core.dayplan

import java.time.LocalDate

interface IDayPlanDownloader {

    fun downloadDayPlansForDates(from: LocalDate, to: LocalDate)

    fun convertToDayPlan()
}
