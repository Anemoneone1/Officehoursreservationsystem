package mainPackage.viewModel

import androidx.lifecycle.ViewModel
import mainPackage.model.OfficeHoursInstance
import mainPackage.model.User
import mainPackage.utils.Checks
import mainPackage.utils.utils1.passwordCheck
import mainPackage.model.RepositoryMockup

class OHRViewModel (): ViewModel(){

    private var currentUser = User()
    var currOfficeHoursInstanceID = null
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

    fun getOfficeHoursList(): MutableList<OfficeHoursInstance>? {
        return currentUser.email?.let { repo.showOfficeHoursList(it) }
    }

    }

