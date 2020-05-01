package gh.ok.mycookbook.domain.diet.dayplan.entity

import gh.ok.mycookbook.domain.recipe.entity.Recipe

data class DayPlan(val meals: List<Recipe>,
                   val forDay: String,
                   val kcal: String = "",
                   val proteinPercentage: Int = 0,
                   val carbohydratesPercentage: Int = 0,
                   val fatsPercentage: Int = 0,
                   val isOriginal: Boolean = true)
