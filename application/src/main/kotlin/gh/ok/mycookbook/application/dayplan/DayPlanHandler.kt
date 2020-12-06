package gh.ok.mycookbook.application.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.dayplan.DayPlan
import gh.ok.mycookbook.domain.dayplan.IDayPlanRepository
import kotlinx.coroutines.flow.Flow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait
import java.time.LocalDate

@Component
class DayPlanHandler(private val dayPlanRepository: IDayPlanRepository) {

    var log: Logger = LoggerFactory.getLogger(DayPlanHandler::class.java)


    suspend fun getDayPlanForDates(request: ServerRequest): ServerResponse {
        log.info(
            "Incoming request for dayplans between: ${
                request.queryParam("from")
                    .get()
            } and ${request.queryParam("to").get()}"
        )
        val fromDate = DateCalculator.toDate(request.queryParam("from").get())
        val toDate = DateCalculator.toDate(request.queryParam("to").get())
        val plans: Flow<DayPlan> = dayPlanRepository.getDayPlanForDates(prepareDatesForGivenRange(fromDate, toDate))
        return ok().contentType(APPLICATION_JSON).bodyAndAwait(plans);
    }

    private fun prepareDatesForGivenRange(fromDate: LocalDate, toDate: LocalDate): List<LocalDate> {
        val dates = mutableListOf<LocalDate>()
        var previous = fromDate
        while (previous.isBefore(toDate)) {
            dates.add(previous)
            previous = previous.plusDays(1)
        }
        dates.add(toDate)
        return dates
    }
}
