package gh.ok.mycookbook.application.dayplan

import gh.ok.mycookbook.core.dayplan.DayPlanService
import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import kotlinx.coroutines.flow.Flow
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class DayPlanHandler(private val dayPlanService: DayPlanService) {

    var log: Logger = LoggerFactory.getLogger(DayPlanHandler::class.java)


    suspend fun getDayPlanForDates(request: ServerRequest): ServerResponse {
        log.info(
            "Incoming request for dayplans between: ${request.queryParam("from")
                .get()} and ${request.queryParam("to").get()}"
        )
        // TODO handle exception when dates cannot be parsed
        val fromDate = DateCalculator.toDate(request.queryParam("from").get())
        val toDate = DateCalculator.toDate(request.queryParam("to").get())
        // TODO handle any other exceptions
        val plans: Flow<DayPlan> = dayPlanService.getDayPlans(fromDate, toDate)
        return ok().contentType(APPLICATION_JSON).bodyAndAwait(plans);
    }
}
