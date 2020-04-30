package gh.ok.mycookbook.domain.recipe.entity

data class Recipe(val category: RecipeCategory,
                  val recipeName: String,
                  val kcal: String = "",
                  val nutrience: String = "",
                  val prepTime: String = "",
                  val ingredients: List<Ingredient> = mutableListOf(),
                  val descriptions: MutableMap<String, List<String>> = mutableMapOf()) {
    // TODO move to store module, because it's only required for savinig to file
    fun toPrettyString() : String {
        var value = ""
        value += "${this.category}: ${this.kcal} | ${this.nutrience} | ${this.prepTime}\n"
        value +="${this.recipeName}\n"
        this.ingredients.forEach {
            value += "${it.product.name} ${it.amount}\n"
        }
        value += "Sposób przygotowania:\n"
        this.descriptions.forEach { name, description ->
            value += "$name\n"
            description.forEach {
                value += "$it\n"
            }

        }
        return value
    }
}
