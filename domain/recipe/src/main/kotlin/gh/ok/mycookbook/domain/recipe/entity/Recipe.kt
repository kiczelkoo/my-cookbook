package gh.ok.mycookbook.domain.recipe.entity

data class Recipe(
    val title: String,
    val ingredients: List<Ingredient> = mutableListOf(),
    val descriptions: MutableMap<Int, String> = mutableMapOf()
)
