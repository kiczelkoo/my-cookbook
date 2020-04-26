package gh.ok.mycookbook.core.diet

import gh.ok.mycookbook.domain.diet.dayplan.entity.DayPlan
import gh.ok.mycookbook.domain.groceries.product.Product
import java.time.LocalDate
import java.util.stream.Collectors

class DayPlanService(private val dayPlanDownloader: IDayPlanDownloader,
                     private val dayPlanRepository: IDayPlanRepository) {

    fun importOriginalDayPlans(fromDate: LocalDate, toDate: LocalDate) {
        val dayPlansToSave: List<DayPlan> = dayPlanDownloader.downloadDayPlansForDates(fromDate, toDate)
        dayPlansToSave.forEach {
            dayPlanRepository.saveAllOriginalDayPlans(dayPlansToSave)
        }
        getAllProducts(dayPlansToSave)
    }

    fun getAllProducts(dayPlans: List<DayPlan>): Set<Product> {
        return dayPlans.flatMap { it.meals }.flatMap { it.ingredients }.map { it.product }
                .stream().collect(Collectors.toSet())
    }

}
