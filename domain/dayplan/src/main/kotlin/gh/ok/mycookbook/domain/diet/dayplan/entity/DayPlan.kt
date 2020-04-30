package gh.ok.mycookbook.domain.diet.dayplan.entity

import gh.ok.mycookbook.domain.recipe.entity.Recipe

data class DayPlan(val meals: List<Recipe>,
                   val forDay: String,
                   val kcal: String = "",
                   val proteinPercentage: Int = 0,
                   val carbohydratesPercentage: Int = 0,
                   val fatsPercentage: Int = 0,
                   val isOriginal: Boolean = true) {

    // TODO move to store module, because it's only required for savinig to file
    fun toPrettyString(): String {
        var value = "Day plan for $forDay\n"
        value += "$kcal kcal\n"
        value += "Rozkład kaloryczności: białka $proteinPercentage%, węglowodany $carbohydratesPercentage%, tłuszcze $fatsPercentage%\n"
        meals.forEach { value += "${it.toPrettyString()}\n" }
        return value
    }

    fun getSummaryContent(): String {
        var value = "Day plan for $forDay\n"
        value += "$kcal kcal\n"
        value += "Rozkład kaloryczności: białka $proteinPercentage%, węglowodany $carbohydratesPercentage%, tłuszcze $fatsPercentage%\n"
        return value
    }
}
