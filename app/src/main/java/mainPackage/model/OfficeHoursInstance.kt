package mainPackage.model

import java.sql.Time
import java.time.DayOfWeek

data class OfficeHoursInstance(val dayOfWeek: DayOfWeek, val timeFrom: Time, val timeTo: Time) {

    override fun toString(): String {
        return "$dayOfWeek, $timeFrom - $timeTo"
    }
}