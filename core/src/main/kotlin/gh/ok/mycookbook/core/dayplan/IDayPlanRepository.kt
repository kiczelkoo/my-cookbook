package gh.ok.mycookbook.core.dayplan

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface IDayPlanRepository {

    fun saveAllOriginalDayPlans(dayPlans: List<DayPlan>)

    fun findDayPlans(dates: List<String>): Flow<DayPlan>

    fun saveRaw(html: String, start: LocalDate)

}
