package gh.ok.mycookbook.application.dayplan

import gh.ok.mycookbook.core.utils.DateCalculator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class DayPlanRoutesConfig(private val dayPlanHandler: DayPlanHandler) {

    @Bean
    fun dayPlanRoutes() = coRouter {
        GET("/day-plan",
                queryParam("from") { DateCalculator.matchesDateFormat(it) }.and(queryParam("to") { DateCalculator.matchesDateFormat(it) }),
                dayPlanHandler::getDayPlanForDates)
    }
}
