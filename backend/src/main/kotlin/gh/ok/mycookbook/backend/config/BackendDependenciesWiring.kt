package gh.ok.mycookbook.backend.config

import gh.ok.mycookbook.core.diet.DayPlanService
import gh.ok.mycookbook.core.diet.IDayPlanRepository
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.integration.dayplan.DayPlanDownloader
import gh.ok.mycookbook.store.dayplan.DayPlanRepository
import gh.ok.mycookbook.store.recipe.RecipeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.mfcookbook.core.services.DayPlanLoginService
import pl.mfcookbook.core.services.DayPlanPageService

@Configuration
class BackendDependenciesWiring(
        @Value("\${integration.diet.plan.user}") private val userName: String,
        @Value("\${integration.diet.plan.pswd}") private val password: String,
        @Value("\${store.recipes.location}") private val location: String) {

    @Bean
    fun dayPlanService(): DayPlanService {
        return DayPlanService(dayPlanDownloader(), dayPlanRepository())
    }

    @Bean
    fun dayPlanRepository(): IDayPlanRepository {
        return DayPlanRepository(location, recipeRepository())
    }

    @Bean
    fun recipeRepository(): IRecipeRepository {
        return RecipeRepository(location)
    }

    @Bean
    fun dayPlanDownloader(): DayPlanDownloader {
        return DayPlanDownloader(DayPlanLoginService(userName, password), DayPlanPageService())
    }

}
