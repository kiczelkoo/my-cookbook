package gh.ok.mycookbook.core.recipe

import io.mockk.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class RecipeServiceUnitTest {

    private val recipeRepository: IRecipeRepository = mockk()
    private val recipeDownloader: IRecipeDownloader = mockk()
    private val recipeService: RecipeService

    init {
        recipeService = RecipeService(recipeRepository, recipeDownloader)
    }

    @BeforeEach
    fun init() {
        clearAllMocks()
    }

    @Nested
    inner class ImportNewestRecipesUnitTest {

        @Test
        fun `should import recipes for given time range`() {
            // given
            val date = LocalDate.now()
            every { recipeDownloader.downloadRecipesForDates(date, date) } returns emptyMap()
            every { recipeRepository.saveAllRecipes(emptyMap()) } just Runs
            // when
            recipeService.importNewestRecipes(date, date)
            // then
            verify(exactly = 0) { recipeRepository.getLastImportDate() }
            verify { recipeDownloader.downloadRecipesForDates(date, date) }
        }

        @Test
        fun `should import recipes for given from date and max to date`() {
            val from = LocalDate.now()
            val maxToDate = LocalDate.now().plusDays(7)
            every { recipeDownloader.downloadRecipesForDates(from, maxToDate) } returns emptyMap()
            every { recipeRepository.saveAllRecipes(emptyMap()) } just Runs
            // when
            recipeService.importNewestRecipes(from, null)
            // then
            verify { recipeDownloader.downloadRecipesForDates(from, maxToDate) }
        }

        @Test
        fun `should import recipes for minimal date and given to date`() {
            val minFromDate = LocalDate.of(2016, 7, 12)
            val to = LocalDate.now()
            every { recipeRepository.getLastImportDate() } returns Optional.empty()
            every { recipeDownloader.downloadRecipesForDates(minFromDate, to) } returns emptyMap()
            every { recipeRepository.saveAllRecipes(emptyMap()) } just Runs
            // when
            recipeService.importNewestRecipes(null, to)
            // then
            verify { recipeDownloader.downloadRecipesForDates(minFromDate, to) }
        }

        @Test
        fun `should import recipes for maximal time range`() {
            val minFromDate = LocalDate.of(2016, 7, 12)
            val maxToDate = LocalDate.now().plusDays(7)
            every { recipeRepository.getLastImportDate() } returns Optional.empty()
            every { recipeDownloader.downloadRecipesForDates(minFromDate, maxToDate) } returns emptyMap()
            every { recipeRepository.saveAllRecipes(emptyMap()) } just Runs
            // when
            recipeService.importNewestRecipes(null, null)
            // then
            verify { recipeDownloader.downloadRecipesForDates(minFromDate, maxToDate) }
        }

        @Test
        fun `should import recipes for latest date and given to date`() {
            val latest = LocalDate.of(2020, 4, 13)
            val from = latest.plusDays(1)
            val to = LocalDate.of(2020, 4, 20)
            every { recipeRepository.getLastImportDate() } returns Optional.of(latest)
            every { recipeDownloader.downloadRecipesForDates(from, to) } returns emptyMap()
            every { recipeRepository.saveAllRecipes(emptyMap()) } just Runs
            // when
            recipeService.importNewestRecipes(null, null)
            // then
            verify { recipeDownloader.downloadRecipesForDates(from, to) }
        }
    }


}
