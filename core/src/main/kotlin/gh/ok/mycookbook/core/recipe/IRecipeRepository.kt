package gh.ok.mycookbook.core.recipe

import java.time.LocalDate
import java.util.*

interface IRecipeRepository {

    fun getLastImportDate(): Optional<LocalDate>

    fun saveAllRecipes(recipes: Map<String, List<String>>)

}
