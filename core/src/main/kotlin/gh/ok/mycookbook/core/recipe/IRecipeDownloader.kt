package gh.ok.mycookbook.core.recipe

import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.time.LocalDate

interface IRecipeDownloader {

    fun downloadRecipesForDates(from: LocalDate, to: LocalDate): Map<String, List<Recipe>>
}
