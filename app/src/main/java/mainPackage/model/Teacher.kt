package mainPackage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mainPackage.utils.utils1.passwordCheck
import java.util.regex.Pattern

const val TEACHER_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@pwr\\.edu\\.pl$"

class Teacher() {
    var nameSurname: String? = null
    var email: String? = null
    private var password: String? = null
    private val officeHoursList = mutableListOf<OfficeHoursInstance>()
    private val officeHours = MutableLiveData<List<OfficeHoursInstance>>()

    init {
        officeHours.value = officeHoursList
    }

    fun equals(teacher: Teacher): Boolean = this.email===teacher.email

    fun setNameSurnameEmail(nameSurname: String, email: String){
        this.nameSurname=nameSurname
        if (teacherEmailCheck(email)) this.email=email
    }

    fun addOfficeHours(officeHoursInstance: OfficeHoursInstance){
        officeHoursList.add(officeHoursInstance)
        officeHours.value=officeHoursList
    }

    fun removeOfficeHours(officeHoursInstance: OfficeHoursInstance){
        officeHoursList.remove(officeHoursInstance)
        officeHours.value=officeHoursList
    }

    fun getOfficeHours() = officeHours as LiveData<List<OfficeHoursInstance>>

    fun updatePassword(password: String){
        if (passwordCheck(password)) {
            this.password=password
        }
    }

    fun teacherEmailCheck(email:String): Boolean{
        var pattern = Pattern.compile(TEACHER_EMAIL_PATTERN)
        var matcher = pattern.matcher(email)
        return matcher.matches()
    }
}