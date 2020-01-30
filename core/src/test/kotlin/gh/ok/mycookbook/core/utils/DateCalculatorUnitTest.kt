package gh.ok.mycookbook.core.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.format.DateTimeParseException


class DateCalculatorUnitTest {

    @Nested
    inner class DateToStringUnitTest {

        @Test
        fun `should return formatted string`() {
            assertThat(DateCalculator.toString(LocalDate.of(2020, 4, 11))).isEqualTo("2020-04-11")
        }
    }

    @Nested
    inner class StringToDateUnitTest {
        @Test
        fun `should return date from formatted string`() {
            assertThat(DateCalculator.toDate("2020-04-11")).isEqualTo(LocalDate.of(2020, 4, 11))
        }

        @Test
        fun `should throw DateTimeParseException if date is not properly formatted`() {
            assertThrows(DateTimeParseException::class.java) { DateCalculator.toDate("2020/04/11") }
        }

        @Test
        fun `should throw DateTimeParseException if string is not a date`() {
            assertThrows(DateTimeParseException::class.java) { DateCalculator.toDate("invalid string") }
        }

        @Test
        fun `should throw DateTimeParseException if string is a date time`() {
            assertThrows(DateTimeParseException::class.java) { DateCalculator.toDate("2020-04-11T12:10:22") }
        }
    }

    @Nested
    inner class MatchesDateFormatUnitTest {
        @Test
        fun `should return false when string doesn't match the date format`() {
            assertThat(DateCalculator.matchesDateFormat("2020-12-11T12:12:34")).isFalse()
        }

        @Test
        fun `should return true when string has the date format`() {
            assertThat(DateCalculator.matchesDateFormat("2020-12-11")).isTrue()
        }

        @Test
        fun `should return true when string has the date format but it's not a date`() {
            assertThat(DateCalculator.matchesDateFormat("2020-31-54")).isTrue()
        }
    }
}





