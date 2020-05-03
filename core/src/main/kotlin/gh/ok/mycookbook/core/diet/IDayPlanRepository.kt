package gh.ok.mycookbook.core.diet

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import kotlinx.coroutines.flow.Flow

interface IDayPlanRepository {

    fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>)

    fun findDayPlans(dates: List<String>): Flow<DayPlan>

}
