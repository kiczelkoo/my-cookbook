package gh.ok.mycookbook.application.config

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.domain.dayplan.IDayPlanRepository
import gh.ok.mycookbook.integration.dayplan.DayPlanDownloader
import gh.ok.mycookbook.store.dayplan.DayPlanFileRepository
import gh.ok.mycookbook.store.recipe.RecipeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.mfcookbook.core.services.DayPlanLoginService
import pl.mfcookbook.core.services.DayPlanPageService

@Configuration
class ApplicationDependenciesWiring(
    @Value("\${integration.diet.plan.user}") private val userName: String,
    @Value("\${integration.diet.plan.pswd}") private val password: String,
    @Value("\${store.recipes.location}") private val location: String
) {

    @Bean
    fun dayPlanRepository(): IDayPlanRepository {
        return DayPlanFileRepository(location)
    }

    @Bean
    fun recipeRepository(): IRecipeRepository {
        return RecipeRepository(location)
    }

    @Bean
    fun dayPlanDownloader(): IDayPlanDownloader {
        return DayPlanDownloader(DayPlanLoginService(userName, password), DayPlanPageService(), dayPlanRepository())
    }

}
