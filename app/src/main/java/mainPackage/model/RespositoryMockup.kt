package mainPackage.model

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.InternalCoroutinesApi

class RepositoryMockup {

    private val database = DatabaseMockup()

    private val userList = mutableListOf<User>()
    private val users = MutableLiveData<List<User>>()


    fun addOfficeHours(officeHoursInstance: OfficeHoursInstance, currUser: User){
        userList.find{ e -> e.equals(currUser)}?.addOfficeHours(officeHoursInstance)
        upd()
    }

    fun setNameSurname(nameSurname: String, currUser: User){
        userList.find{ e -> e.equals(currUser)}?.setNameSurname(nameSurname)
        upd()
    }

    fun updatePassword(password: String, currUser: User) {
        userList.find{ e -> e.equals(currUser)}?.updatePassword(password)
        upd()
    }

    fun getOfficeHours(currUser: User) = userList.find{ e -> e.equals(currUser)}?.getOfficeHours()

    fun addOfficeHoursFromTeacher (currUser: User, teacherEmail: String): Int{
        var counter = 0
        var tempUser = User()
        tempUser.setEmail(teacherEmail)
        tempUser = userList.find{ e -> e.equals(tempUser)}!!

        for(e in tempUser.officeHoursList){
            addOfficeHours(e, currUser)
            counter++
        }
        return counter
    }

    fun addUser(newUser: User, email: String):Int{
        var res: Int = newUser.setEmail(email)
        return if (res == -1 || userList.find { e -> e.equals(newUser) }!=null) -1
        else {
            userList.add(newUser)
            upd()
            res
        }
    }

    fun findCurrentUser(email: String):User?{
        var tempUser = User()
        tempUser.setEmail(email)
        return userList.find { e -> e.equals(tempUser)}
    }

    fun getUserList(): List<User>? = database.getUserList()

    private fun upd(){
        this.users.value=this.userList
        database.usersdbm=users
    }
}