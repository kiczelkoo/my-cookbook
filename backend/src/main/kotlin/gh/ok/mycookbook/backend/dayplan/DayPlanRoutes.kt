package gh.ok.mycookbook.backend.dayplan

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class DayPlanRoutes(private val dayPlanHandler: DayPlanHandler) {

    @Bean
    fun dayPlanRoute() = coRouter {
        GET("/test", dayPlanHandler::getDayPlanForDates)
    }
}
