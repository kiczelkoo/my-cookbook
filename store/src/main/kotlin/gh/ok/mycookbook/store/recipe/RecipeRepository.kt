package gh.ok.mycookbook.store.recipe

import gh.ok.mycookbook.core.recipe.IRecipeRepository
import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.io.File

class RecipeRepository(private val location: String) : IRecipeRepository {

    val recipeConverter: RecipeConverter = RecipeConverter()

    override fun saveRecipes(path: String, recipes: List<Recipe>) {
        recipes.forEachIndexed { index, recipe ->
            val file = File("$path/$index-${recipe.title}.txt")
                .also { file -> file.parentFile.mkdirs() }
            file.writeText(recipeConverter.createRecipeFileContent(recipe))
        }
    }
}
