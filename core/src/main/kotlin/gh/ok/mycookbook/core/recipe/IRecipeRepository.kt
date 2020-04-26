package gh.ok.mycookbook.core.recipe

import gh.ok.mycookbook.domain.recipe.entity.Recipe

interface IRecipeRepository {

    fun saveRecipes(path: String, recipes: List<Recipe>)

}
