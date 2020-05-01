package gh.ok.mycookbook.domain.recipe.entity

data class Recipe(val category: RecipeCategory,
                  val recipeName: String,
                  val kcal: String = "",
                  val nutrience: String = "",
                  val prepTime: String = "",
                  val ingredients: List<Ingredient> = mutableListOf(),
                  val descriptions: MutableMap<String, MutableList<String>> = mutableMapOf())
