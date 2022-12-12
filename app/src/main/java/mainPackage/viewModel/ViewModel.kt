package mainPackage.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mainPackage.model.OfficeHoursInstance
import mainPackage.model.RepositoryMockup
import mainPackage.model.User
import java.sql.Time
import java.time.DayOfWeek

class ViewModel (email: String): ViewModel(){

    private val repository = RepositoryMockup()

    private var currentUser = repository.findCurrentUser(email)
    private val userList = repository.getUserList()

    fun addOfficeHours(dayOfWeek: DayOfWeek, timeFrom: Time, timeTo: Time) =
        currentUser?.let {
            repository.addOfficeHours(OfficeHoursInstance(dayOfWeek, timeFrom, timeTo),
                it
            )
        }

    fun updatePassword(password: String) = currentUser?.let {
        repository.updatePassword(password,
            it
        )
    }

    fun updateNameSurnameEmail(nameSurname: String, email: String) = currentUser?.let { repository.setNameSurname(nameSurname, it) }

    fun addUser(email: String): Int {
        val newUser = User()
        val res = repository.addUser(newUser, email)
        currentUser = repository.findCurrentUser(email)
        return res
    }

    fun getOfficeHours() = currentUser?.let { repository.getOfficeHours(it) }

    fun addOfficeHoursFromTeacher(email: String) =
        currentUser?.let { repository.addOfficeHoursFromTeacher(it, email) }


    }

