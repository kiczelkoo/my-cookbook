package gh.ok.mycookbook.integration.dayplan

import gh.ok.mycookbook.core.dayplan.IDayPlanDownloader
import gh.ok.mycookbook.core.dayplan.IDayPlanRepository
import gh.ok.mycookbook.core.utils.DateCalculator
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.openqa.selenium.chrome.ChromeDriver
import pl.mfcookbook.core.services.DayPlanLoginService
import pl.mfcookbook.core.services.DayPlanPageService
import java.time.LocalDate

// todo it'll be refactored during implementation of import task
class DayPlanDownloader(
    private val loginService: DayPlanLoginService,
    private val dayPlanPageService: DayPlanPageService,
    private val dayPlanRepository: IDayPlanRepository
) : IDayPlanDownloader {

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

            val mealId = it.attributes().get("name")
            it.children()[0].children()
                .filter { it.hasClass("row") || it.hasClass("d-none d-sm-block") }
                .filter { !it.hasClass("mt-5 row") }
                .forEachIndexed { index, mealElement ->
                    if (isItMealSummary(mealElement)) processSummary(mealElement) else processDish(mealElement)
                }

        }

    }

    private fun processSummary(mealElement: Element) {
        println(mealElement.text())
    }

    private fun isItMealSummary(mealElement: Element): Boolean {
        return mealElement.hasClass("d-none d-sm-block")
    }

    private fun processDish(mealElement: Element) {
        mealElement.children().forEachIndexed { index, element ->
            if (index == 0)
                println(element.getElementsByClass("dishName").text())
            processDishIngredients(element.children().filter { it.hasClass("ingredients") })
            if (index == 1) processDescription(element)
        }
    }

    private fun processDescription(element: Element) {
        println(element.text())
    }

    private fun processDishIngredients(ingredientLists: List<Element>) {
        ingredientLists.forEach {
            it.children().forEach {
                val amount = it.children()[0].text()
                val product = it.textNodes().filter { !it.isBlank }.first().text()
                println("$product $amount")

                val substitutes = it.getElementsByClass("collapse")[0].getElementsByTag("li");
                if (!substitutes.isEmpty())
                    println("Substitutes")
                substitutes.forEach {
                    val amountSub = it.children()[0].text()
                    val productSub = it.text().replace(amountSub, "")
                    println("   $productSub $amountSub")
                }
            }
        }

    }


}
