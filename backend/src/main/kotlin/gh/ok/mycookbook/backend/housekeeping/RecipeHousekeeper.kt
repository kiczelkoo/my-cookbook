package gh.ok.mycookbook.backend.housekeeping

import gh.ok.mycookbook.core.recipe.RecipeService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class RecipeHousekeeper(private val recipeService: RecipeService) {

    // TODO remove just for testing
    @Scheduled(fixedDelay = 50000)
    fun importRecipes() {
//        recipeService.importNewestRecipes(LocalDate.of(2019, 12, 4), LocalDate.of(2019, 12, 5))
    }
}
