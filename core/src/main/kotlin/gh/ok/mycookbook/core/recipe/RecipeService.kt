package gh.ok.mycookbook.core.recipe

import java.time.LocalDate

class RecipeService(private val recipeRepository: IRecipeRepository,
                    private val recipeDownloader: IRecipeDownloader) {
    companion object {
        private val FIRST_DATE = LocalDate.of(2016, 7, 12)
        private val LAST_DATE = LocalDate.now().plusDays(7)
    }


    fun importNewestRecipes(fromDate: LocalDate?, toDate: LocalDate?) {
        val from = fromDate ?: recipeRepository.getLastImportDate().map { it.plusDays(1) }.orElse(FIRST_DATE)
        val to = toDate ?: LAST_DATE
        val recipesToSave = recipeDownloader.downloadRecipesForDates(from, to)
        recipeRepository.saveAllRecipes(recipesToSave)
    }


}
