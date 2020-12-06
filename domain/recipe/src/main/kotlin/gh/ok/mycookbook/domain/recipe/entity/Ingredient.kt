package gh.ok.mycookbook.domain.recipe.entity

data class Ingredient(
    val productName: String,
    val amounts: List<Pair<Double, IngredientUnit>>
)
