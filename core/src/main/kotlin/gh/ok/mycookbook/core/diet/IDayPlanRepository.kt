package gh.ok.mycookbook.core.diet

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan

interface IDayPlanRepository {

    fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>)

    fun findDayPlansForDates(dates: List<String>): List<DayPlan>

}
