package mainPackage.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import mainPackage.utils.utils1
import java.util.regex.Pattern

const val STUDENT_EMAIL_PATTERN = "^[a-zA-Z]+\\.[a-zA-Z]+@student\\.pwr\\.edu\\.pl$"


class Student() {
    var nameSurname: String? = null
    var email: String? = null
    private var password: String? = null
    private val coursesList = mutableListOf<Course>()
    private var courses = MutableLiveData<List<Course>>()

    fun setNameSurnameEmail(nameSurname: String, email: String){
        this.nameSurname=nameSurname
        if (studentEmailCheck(email)) this.email=email
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

    fun updatePassword(password: String){
        if (utils1.passwordCheck(password)) {
            this.password=password
        }
    }

    fun studentEmailCheck(email:String): Boolean{
        var pattern = Pattern.compile(STUDENT_EMAIL_PATTERN)
        var matcher = pattern.matcher(email)
        return matcher.matches()
    }
}