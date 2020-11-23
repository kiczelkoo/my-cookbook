package gh.ok.mycookbook.domain.diet.dayplan.entity

import gh.ok.mycookbook.domain.recipe.entity.Recipe
import java.util.*

data class DayPlan(
    val meals: List<Recipe>,
    val date: Date,
    val kcal: Int = 0,
    val proteins: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0
)
