package gh.ok.mycookbook.domain.dayplan

import java.time.LocalDate

data class DayPlan(
    val meals: List<Meal>,
    val date: LocalDate,
    val kcal: Int = 0,
    val proteins: Int = 0,
    val carbs: Int = 0,
    val fats: Int = 0
)
