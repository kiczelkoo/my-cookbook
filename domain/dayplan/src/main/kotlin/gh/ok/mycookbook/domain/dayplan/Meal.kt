package gh.ok.mycookbook.domain.dayplan

import gh.ok.mycookbook.domain.recipe.entity.Recipe

data class Meal(
    val count: Int,
    val category: MealCategory,
    val prepareTime: Int,
    val recipes: List<Recipe>,
    val kcal: Int = 0,
    val proteins: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0
)
