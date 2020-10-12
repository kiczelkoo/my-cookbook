package gh.ok.mycookbook.integration.dayplan

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import gh.ok.mycookbook.core.dayplan.IDayPlanRepository
import gh.ok.mycookbook.core.utils.DateCalculator
import gh.ok.mycookbook.domain.recipe.entity.Ingredient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.openqa.selenium.chrome.ChromeDriver
import pl.mfcookbook.core.services.DayPlanLoginService
import pl.mfcookbook.core.services.DayPlanPageService
import java.time.LocalDate


class DayPlanDownloader(private val loginService: DayPlanLoginService,
                        private val dayPlanPageService: DayPlanPageService,
                        private val dayPlanRepository: IDayPlanRepository) : IDayPlanDownloader {

   override fun downloadDayPlansForDates(from: LocalDate, to: LocalDate) {
        val driver = ChromeDriver()
        loginService.login(driver)
        var start = from
        while (start.compareTo(to) <= 0) {
            val rawDayPlan = dayPlanPageService.getRawDayPLan(DateCalculator.toString(start), driver)
            if (rawDayPlan.isPresent) {
                dayPlanRepository.saveRaw(rawDayPlan.get(), start)
            }
            start = start.plusDays(1)
        }
        driver.close()
    }

    override fun convertToDayPlan() {
        val fileContent = DayPlanDownloader::class.java.getResource("/2020-05-03.html").readText()
        val doc: Document = Jsoup.parse(fileContent)


        // read meals
        doc.getElementsByClass("meal").forEach {

            val mealId = it.attributes().get("name") // meal id
            println(mealId)
            it.children().forEach {
                val numOfChildren = it.children().filter { !it.text().isBlank() }.size
                it.children().filter { !it.text().isBlank() }.forEachIndexed{ index, el ->
                    if (index == 1) println("meal name and summary: ${el.text()}")
                    if (index > 1 && index < numOfChildren - 1) processDish(el)
                }
            }


//
//            val dishesNames = it.getElementsByClass("dishName") // list of dishes' names
//            val ingredients = it.getElementsByClass("ingredients")
//            showIngredients(ingredients)
//            it.getElementsByClass("ingredients")[0].getElementsByTag("li")[0].textNodes().filter { !it.isBlank }.first().text() // 2nd ingredient name
//            it.getElementsByClass("ingredients")[0].getElementsByTag("li")[0].getElementsByTag("span")[0].text() // quantity of first ingredient
//
//            it.getElementsByClass("");

        }
        // read dishes
                // read product lists
                    // read substitutes
                // read description
        // read day summary
        doc.getElementsByClass("summary")



//        val sections = fileContent.split("<section class=").filter { it.startsWith("\"meal") || it.startsWith("\"summary") }
//        sections.forEach {
//            val doc: Document = Jsoup.parse("<section class=$it")
//            println(doc.body().text())
//        }
    }

    private fun processDish(el: Element) {
        el.children().forEach {
            println(it.text())
        }
    }

    private fun showIngredients(ingredients: Elements) {
        ingredients.forEach {
            it.getElementsByTag("li").forEach {
                val ing = it.textNodes().filter { !it.isBlank }
                val name = if (ing.isNotEmpty()) ing.first().text() else "tu nie ma składnika"
                val quantity = it.getElementsByTag("span")[0].text()
                println("$name: $quantity")
            }
        }
    }


}
