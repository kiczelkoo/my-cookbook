package gh.ok.mycookbook.store.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.IDayPlanRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate

class DayPlanFileRepository(private val dayPlansLocation: String) : IDayPlanRepository {

    private val dayPlanJsonConverter: DayPlanJsonConverter = DayPlanJsonConverter()

    override fun getDayPlanForDates(dates: List<LocalDate>): Flow<DayPlan> = flow {
        println("start method")
        dates.forEach { date ->
            val dateStr = DateCalculator.toString(date)
            val fileContent = getFileContent(dateStr)
            emit(dayPlanJsonConverter.convertToDayPlan(fileContent))
        }
    }

    private fun getFileContent(dateStr: String): String {
        // TODO future implementation will read from disk
//        val fileContent = File("$dayPlansLocation/$dateStr").listFiles().get(0).readText()
        return DayPlanFileRepository::class.java.getResource("/day-plan-23-11-2020.json").readText()
    }

}
