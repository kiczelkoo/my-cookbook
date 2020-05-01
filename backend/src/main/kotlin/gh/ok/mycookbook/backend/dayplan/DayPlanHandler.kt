package gh.ok.mycookbook.backend.dayplan

import org.springframework.http.MediaType.TEXT_PLAIN
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class DayPlanHandler {

    suspend fun getDayPlanForDates(request: ServerRequest): ServerResponse {
        return ok().contentType(TEXT_PLAIN).bodyValueAndAwait("Hello World")
    }
}
