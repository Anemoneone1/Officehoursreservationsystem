package mainPackage.viewModel

import androidx.lifecycle.ViewModel
import mainPackage.model.OfficeHoursInstance
import mainPackage.model.RepositoryMockup
import java.sql.Time
import java.time.DayOfWeek

class TeacherViewModel (private val RepositoryMockup: RepositoryMockup): ViewModel(){

    fun addOfficeHours(dayOfWeek: DayOfWeek, timeFrom: Time, timeTo: Time) = RepositoryMockup.addOfficeHours(OfficeHoursInstance(dayOfWeek, timeFrom, timeTo))

    fun updatePassword(password: String) = RepositoryMockup.updatePassword(password)

    fun updateNameSurnameEmail(nameSurname: String, email: String) = RepositoryMockup.setNameSurnameEmail(nameSurname, email)
}
