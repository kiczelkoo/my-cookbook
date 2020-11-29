package gh.ok.mycookbook.domain.dayplan

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface IDayPlanRepository {

    fun getDayPlanForDates(dates: List<LocalDate>): Flow<DayPlan>
}
