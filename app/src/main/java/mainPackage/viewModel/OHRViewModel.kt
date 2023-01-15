package mainPackage.viewModel

import androidx.lifecycle.ViewModel
import mainPackage.model.OfficeHoursInstance
import mainPackage.model.User
import mainPackage.utils.Checks
import mainPackage.utils.utils1.passwordCheck
import mainPackage.model.RepositoryMockup

class OHRViewModel (): ViewModel(){

    private var currentUser = User()
    var ha = currentUser?.password
    val repo = RepositoryMockup()
//    private val userList = repository.getUserList()

    fun login(email: String, password: String): Checks{
        var currUser = User()
        if (currUser.setEmail(email)==Checks.INCORRECT_EMAIL_FORM) return Checks.INCORRECT_EMAIL_FORM
        else if (passwordCheck(password)==Checks.INCORRECT_PASSWORD_FORM) return  Checks.INCORRECT_PASSWORD_FORM
        currUser.password=password
        currentUser=currUser
        return repo.userLogin(currentUser)
    }

    fun getOfficeHoursList(): List<OfficeHoursInstance>{
        return showOfficeHoursList()
    }















//    fun addOfficeHours(dayOfWeek: DayOfWeek, timeFrom: Time, timeTo: Time) =
//        currentUser?.let {
//            repository.addOfficeHours(OfficeHoursInstance(dayOfWeek, timeFrom, timeTo),
//                it
//            )
//        }
//
//    fun updatePassword(password: String) = currentUser?.let {
//        repository.updatePassword(password,
//            it
//        )
//    }
//
//    fun updateNameSurnameEmail(nameSurname: String, email: String) = currentUser?.let { repository.setNameSurname(nameSurname, it) }
//
//    fun addUser(email: String): Int {
//        val newUser = User()
//        val res = repository.addUser(newUser, email)
//        currentUser = repository.findCurrentUser(email)
//        return res
//    }
//
//    fun getOfficeHours() = currentUser?.let { repository.getOfficeHours(it) }
//
//    fun addOfficeHoursFromTeacher(email: String) =
//        currentUser?.let { repository.addOfficeHoursFromTeacher(it, email) }


    }

