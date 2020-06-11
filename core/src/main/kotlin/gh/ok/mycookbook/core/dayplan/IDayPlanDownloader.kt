package gh.ok.mycookbook.core.dayplan

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import java.time.LocalDate

interface IDayPlanDownloader {

    fun downloadDayPlansForDates(from: LocalDate, to: LocalDate): List<DayPlan>
}
