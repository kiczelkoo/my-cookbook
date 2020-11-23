package gh.ok.mycookbook.domain.recipe.entity

data class Ingredient(
    val productName: String,
    val amount: Int = 0,
    val unit: IngredientUnit?,
    val amountSi: Int = 0,
    val unitSi: IngredientUnitSi?
)
