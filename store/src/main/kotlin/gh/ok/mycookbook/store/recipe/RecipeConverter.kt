package gh.ok.mycookbook.store.recipe

import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.io.File

class RecipeConverter {

    fun createRecipeFileContent(recipe: Recipe): String {
        var value = ""
        return value
    }

    fun createRecipesFromFiles(recipesFiles: List<File>): List<Recipe> {
        val recipes = mutableListOf<Recipe>()
        return recipes
    }
}
