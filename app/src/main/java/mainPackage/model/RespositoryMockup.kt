package mainPackage.model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.InternalCoroutinesApi

class RepositoryMockup private constructor() {

    var currTeacher = Teacher()
    private val teachersList = mutableListOf<Teacher>()
    private val teachers = MutableLiveData<List<Teacher>>()
    private val studentsList = mutableListOf<Student>()
    private val student = MutableLiveData<List<Student>>()

    fun addOfficeHours(officeHoursInstance: OfficeHoursInstance){
        teachersList.find{ e -> e.equals(currTeacher)}?.addOfficeHours(officeHoursInstance)
    }

    fun setNameSurnameEmail(nameSurname: String, email: String){
        teachersList.setNameSurnameEmail(nameSurname, email)
    }

    fun updatePassword(password: String){
        teachersList.updatePassword(password)
    }

    fun getOfficeHours() = teachersList.getOfficeHours()

    companion object{@Volatile private var instance: RepositoryMockup? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(teacher: Teacher)= instance?: kotlinx.coroutines.internal.synchronized(this) {
            instance ?: RepositoryMockup(teacher).also { instance = it }
        }
    }
}