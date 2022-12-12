package mainPackage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mainPackage.utils.utils1.passwordCheck
import java.util.regex.Pattern

const val TEACHER_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@pwr\\.edu\\.pl$"
const val STUDENT_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@student\\.pwr\\.edu\\.pl$"

class User {
    var nameSurname: String? = null
    var email: String? = null
    private var password: String? = null
    private var isATeacher: Boolean? = null
    val officeHoursList = mutableListOf<OfficeHoursInstance>()
    val officeHours = MutableLiveData<List<OfficeHoursInstance>>()
    private val coursesList = mutableListOf<Course>()
    private var courses = MutableLiveData<List<Course>>()

    init {
        officeHours.value = officeHoursList
        courses.value=coursesList
    }

    fun equals(user: User): Boolean = this.email===user.email

    @JvmName("setNameSurname1")
    fun setNameSurname(nameSurname: String){
        this.nameSurname=nameSurname
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


    fun setEmail(email:String): Int{
        var teacherPattern = Pattern.compile(TEACHER_EMAIL_PATTERN)
        var matcher1 = teacherPattern.matcher(email)
        var studentPattern = Pattern.compile(STUDENT_EMAIL_PATTERN)
        var matcher2 = studentPattern.matcher(email)
        if (matcher1.matches()){
            isATeacher=true
            this.email=email
            return 1
        }
        else if (matcher2.matches()){
            isATeacher=false
            this.email=email
            return 0
        }
        else return -1
    }

    fun  addCourse(course: Course){
        coursesList.add(course)
        courses.value=coursesList
    }

    fun removeCourse(course: Course){
        coursesList.remove(course)
        courses.value=coursesList
    }

    fun getCourses()= courses as LiveData<List<Course>>
}