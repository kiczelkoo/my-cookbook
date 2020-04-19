package gh.ok.mycookbook.core.recipe

import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.time.LocalDate
import java.util.*

interface IRecipeRepository {

    fun getLastImportDate(): Optional<LocalDate>

    fun saveAllRecipes(recipes: Map<String, List<Recipe>>)

}
