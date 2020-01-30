package gh.ok.mycookbook.core.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DateCalculator {

    companion object {

        const val DATE_PATTERN = "yyyy-MM-dd"

        fun toString(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern(DATE_PATTERN))

        fun toDate(dateStr: String): LocalDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DATE_PATTERN))

        fun matchesDateFormat(value: String) = value.matches("^\\d{4}-\\d{2}-\\d{2}\$".toRegex())

    }

}
