package gh.ok.mycookbook.domain.groceries.product

data class Product(val name: String,
                   val category: ProductCategory = ProductCategory.OTHER,
                   val kcalInUnit: String = "",
                   val nutrience: String = "")
