package gh.ok.mycookbook.core.recipe

import java.time.LocalDate

interface IRecipeDownloader {

    fun downloadRecipesForDates(from: LocalDate, to: LocalDate): Map<String, List<String>>
}
