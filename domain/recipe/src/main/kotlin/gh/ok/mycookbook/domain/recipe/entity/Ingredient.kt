package gh.ok.mycookbook.domain.recipe.entity

import gh.ok.mycookbook.domain.groceries.product.Product

data class Ingredient(val product: Product, val amount: String)
