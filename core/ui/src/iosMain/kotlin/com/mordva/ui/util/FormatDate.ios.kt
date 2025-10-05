package com.mordva.ui.util

import kotlinx.datetime.LocalDate
import platform.Foundation.NSCalendar
import platform.Foundation.NSCalendarUnitYear
import platform.Foundation.NSDate
import platform.Foundation.NSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.localeWithLocaleIdentifier

actual object FormatDate {
    actual fun getCurrentYear(): Int {
        val comps = NSCalendar.currentCalendar.components(
            NSCalendarUnitYear,
            fromDate = NSDate()
        )
        return comps.year.toInt()
    }

    actual fun formatDate(date: String): String {
        return try {
            val datePart = date.substringBefore("T")
            val parsedDate = parseDateWithZeroYearSupport(datePart)

            val formatter = NSDateFormatter().apply {
                dateFormat = "d MMMM yyyy"
                locale = NSLocale.localeWithLocaleIdentifier("ru_RU")
            }

            val comps = NSDateComponents().apply {
                year = parsedDate.year.toLong()
                month = parsedDate.monthNumber.toLong()
                day = parsedDate.dayOfMonth.toLong()
            }

            val calendar = NSCalendar.currentCalendar
            val nsDate = calendar.dateFromComponents(comps)!!

            formatter.stringFromDate(nsDate)
        } catch (e: Exception) {
            ""
        }
    }

    private fun parseDateWithZeroYearSupport(dateStr: String): LocalDate {
        return try {
            LocalDate.parse(dateStr)
        } catch (e: Exception) {
            if (dateStr.startsWith("0000")) {
                LocalDate.parse("0001" + dateStr.removePrefix("0000"))
            } else {
                throw e
            }
        }
    }
}