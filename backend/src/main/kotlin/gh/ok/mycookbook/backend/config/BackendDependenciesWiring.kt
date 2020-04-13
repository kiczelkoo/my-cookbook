package gh.ok.mycookbook.backend.config

import gh.ok.mycookbook.core.recipe.RecipeService
import gh.ok.mycookbook.integration.recipe.RecipeDownloader
import gh.ok.mycookbook.store.recipe.RecipeRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.mfcookbook.core.services.RecipeDayPlanService
import pl.mfcookbook.core.services.RecipeLoginService

@Configuration
class BackendDependenciesWiring(
        @Value("\${integration.diet.plan.user}")private val userName: String,
        @Value("\${integration.diet.plan.pswd}")private val password: String,
        @Value("\${store.recipes.location}")private val location: String) {

    @Bean
    fun recipeService(): RecipeService {
        return RecipeService(recipeRepository(), recipeDownloader())
    }

    @Bean
    fun recipeRepository(): RecipeRepository {
        return RecipeRepository(location)
    }

    @Bean
    fun recipeDownloader(): RecipeDownloader {
        return RecipeDownloader(RecipeLoginService(userName, password), RecipeDayPlanService())
    }

}
