package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.IDayPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.time.LocalDate

class DayPlanRepository(private val dayPlansLocation: String) : IDayPlanRepository {

    private val dayPlanJsonConverter: DayPlanJsonConverter = DayPlanJsonConverter()

    override fun getDayPlanForDates(dates: List<LocalDate>): Flow<DayPlan> = flow {
        println("start method")
        dates.forEach { date ->
            val dateStr = DateCalculator.toString(date)
            val file = File("$dayPlansLocation/$dateStr").listFiles().get(0)
            emit(dayPlanJsonConverter.convertToDayPlan(file.readText()))
        }
    }

    fun getDayPlan(dates: List<LocalDate>) {
        dates.forEach { date ->
            val dateStr = DateCalculator.toString(date)
            val file = File("$dayPlansLocation/$dateStr").listFiles().get(0)
            println(dayPlanJsonConverter.convertToDayPlan(file.readText()))
        }
    }

}
