package gh.ok.mycookbook.store.recipe

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import java.io.File
import java.time.LocalDate

class RecipeRepositoryUnitTest {

    private val tmpDirectory: File = createTempDir("test", null, File("/tmp"))
    private val recipeRepository: RecipeRepository

    init {
        recipeRepository = RecipeRepository(tmpDirectory.path)
    }

    @AfterAll
    fun clean() {
        tmpDirectory.deleteRecursively()
    }


    @Nested
    inner class GetLastImportDateUnitTest {

        @Test
        fun `should return empty optional if directory is empty`() {
            // given when then
            Assertions.assertFalse(recipeRepository.getLastImportDate().isPresent)

        }

        @Test
        fun `should return empty optional if directory has no subdirectories`() {
            // given
            File(tmpDirectory.path.plus("/test.txt")).createNewFile()
            // when then
            Assertions.assertFalse(recipeRepository.getLastImportDate().isPresent)
        }

        @Test
        fun `should return empty optional if none of subdirectories matches date format`() {
            // given
            File(tmpDirectory.path.plus("/test")).mkdir()
            File(tmpDirectory.path.plus("/notValid")).mkdir()
            // when then
            Assertions.assertFalse(recipeRepository.getLastImportDate().isPresent)
        }

        @Test
        fun `should return optional of latest date`() {
            // given
            File(tmpDirectory.path.plus("/2019-12-31")).mkdir()
            File(tmpDirectory.path.plus("/2020-04-11")).mkdir()
            // when
            val lastImportDate = recipeRepository.getLastImportDate()
            // then
            assertThat(lastImportDate.isPresent).isTrue()
            assertThat(lastImportDate.get()).isEqualTo(LocalDate.of(2020, 4, 11))
        }

    }

    @Nested
    inner class SaveAllRecipesUnitTest {

        @Test
        fun `should do nothing if map is empty`() {
            // given
            val noElementsBefore = tmpDirectory.listFiles()?.size
            val recipesPerDay = emptyMap<String, List<String>>()
            // when
            recipeRepository.saveAllRecipes(recipesPerDay)
            // then
            assertThat(tmpDirectory.listFiles()).hasSize(noElementsBefore!!)
        }

        @Test
        fun `should do nothing if list of recipes for a day is empty`() {
            // given
            val noElementsBefore = tmpDirectory.listFiles()?.size
            val recipesPerDay = mapOf("2020-04-01" to emptyList<String>())
            // when
            recipeRepository.saveAllRecipes(recipesPerDay)
            // then
            assertThat(tmpDirectory.listFiles()).hasSize(noElementsBefore!!)
        }

        @Test
        fun `should save recipes in separate directories`() {
            // given
            val expectedDirName = "2020-04-01"
            val recipesPerDay = mapOf(expectedDirName to listOf("breakfast", "snack"))
            // when
            recipeRepository.saveAllRecipes(recipesPerDay)
            //end
            val newDir = tmpDirectory.listFiles()?.filter { it.name.equals(expectedDirName) }
            assertThat(newDir).hasSize(1)
            val listOfRecipes = newDir?.first()?.listFiles()?.map { it.name }
            assertThat(listOfRecipes).hasSize(2)
            assertThat(listOfRecipes).containsAll(listOf("0.txt", "1.txt"))
        }

    }


}
