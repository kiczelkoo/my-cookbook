package gh.ok.mycookbook.store.recipe

import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.io.File
import java.time.LocalDate
import java.util.*

class RecipeRepository(val recipesLocation: String) : IRecipeRepository {

    override fun getLastImportDate(): Optional<LocalDate> {
        val latestDirName = File(recipesLocation).listFiles()
                ?.filter { file -> file.isDirectory }
                ?.map { it.name }
                ?.filter { DateCalculator.matchesDateFormat(it) }
                ?.sortedDescending()
                ?.firstOrNull()
        return if (latestDirName != null) Optional.of(DateCalculator.toDate(latestDirName)) else Optional.empty()
    }

    override fun saveAllRecipes(recipes: Map<String, List<Recipe>>) {
        recipes.forEach { (date, recipesForOneDay) -> saveRecipes(date, recipesForOneDay) }
    }

    private fun saveRecipes(date: String, recipes: List<Recipe>) {
        recipes.forEachIndexed { count, recipe ->
            val file = File(recipesLocation.plus("/${date}/${count}.txt"))
                    .also { file -> file.parentFile.mkdirs() }
            file.writeText(recipe.toPrettyString())
        }
    }
}
