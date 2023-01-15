package mainPackage.model

import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException
import mainPackage.utils.Checks

const val TAG = "FIRESTORE"

class RepositoryMockup {

    //--------------------------------

    //Firebase functions

    fun writeNewUser(pass: String, email: String, isATeacher: Boolean, nameSurname: String) {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Users").document(email)

        val newUser = hashMapOf(
            "is_a_teacher" to isATeacher,
            "pass" to pass,
            "name_surname" to nameSurname
        )

        myRef.set(newUser)
            .addOnSuccessListener { Log.d(TAG, "User successfully added") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing user", e) }

    }

    fun userLogin(user: User?) : Checks {
        val database = FirebaseFirestore.getInstance()
        val myRef = user?.email?.let { database.collection("Users").document(it) }
        var pass = ""
        var final = 0
        if (myRef != null) {
            myRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        pass = document.get("pass") as String
                        if(pass == user?.password){
                            final = 2
                        } else {
                            final = 1
                        }
                        Log.d(TAG, "Pass successfully checked")
                    } else {
                        final = 3
                        Log.d(TAG, "Is empty")
                    }
                }
                .addOnFailureListener { exception ->
                    if (exception is FirebaseFirestoreException) {
                        Log.e(TAG, "Error getting document: ", exception)
                    }
                }
        }
        when (final) {
            1 -> return Checks.FAILED_CHECK
            2 -> return Checks.PASSED
            3 -> return Checks.NEW_USER_CREATED
        }
        return Checks.FAILED_CHECK
    }

    fun readNameSurnameByEmail(email: String): String {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Users").document(email)
        var fieldValue = ""

        myRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fieldValue = document.get("name_surname") as String
                    Log.d(TAG, "Name and surname successfully read")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        return fieldValue
    }

    fun readIsATeacherByEmail(email: String): Boolean {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Users").document(email)
        var fieldValue = false


        myRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    fieldValue = document.get("is_a_teacher") as Boolean
                    Log.d(TAG, "Name and surname successfully read")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        return fieldValue
    }

    fun writeCourse(courseId: String, email: String, timeFrom: String, timeTo: String, weeksCount: Int) {
        val database = FirebaseFirestore.getInstance()
        var nameAndSurname = ""
        val emailRef = database.collection("Users").document(email)

        emailRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nameAndSurname = document.get("name_surname") as String
                    Log.d(TAG, "Name and surname successfully read In Method writeCourse")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }

        val myRef = database.collection("Course").document(courseId)

        val newCourse = hashMapOf(
            "name_surname" to nameAndSurname,
            "time_from" to timeFrom,
            "time_to" to timeTo,
            "weeks_count" to weeksCount
        )

        myRef.set(newCourse)
            .addOnSuccessListener { Log.d(TAG, "Course successfully added") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing Course", e) }

    }

    fun writeOfficeHoursInstance(email: String, timeFrom: String, timeTo: String, id: String) {
        val database = FirebaseFirestore.getInstance()
        var id = ""
        val myRef = database.collection("OfficeHoursInstance").document()
        id = myRef.id
        val newInstance = hashMapOf(
            "email" to email,
            "time_from" to timeFrom,
            "time_to" to timeTo,
            "id" to id
        )

        myRef.set(newInstance)
            .addOnSuccessListener { Log.d(TAG, "Instance successfully added") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing Instance", e) }



    }

    fun readOfficeHoursInstanceTeacher(email: String) : MutableList<OfficeHoursInstance>{
        var timeFrom = ""
        var timeTo = ""
        var userEmail = ""
        var code = ""
        var list = mutableListOf<OfficeHoursInstance>()
        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("OfficeHoursInstance")
            .whereEqualTo("email", email)
        ref.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    timeFrom = document.get("time_from") as String
                    timeTo = document.get("time_to") as String
                    userEmail = document.get("email") as String
                    code = document.get("id") as String
                    list.add(OfficeHoursInstance(userEmail, timeFrom, timeTo, code))
                    Log.d(TAG, " Time instance successfully read")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        return list
    }

    fun updateUserOfficeHoursList(email: String, code: String){
        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("Users").document(email)
        var list = ""

        ref.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    list = document.get("office_hours_list") as String
                    Log.d(TAG, "office_hours_list successfully read")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        if (list.isEmpty()){
            list = code
        }
        else {
            list + ", " + code
        }
        ref.update("office_hours_list", list)
    }

    fun showOfficeHoursList(email: String) : MutableList<String>{
        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("Users").document(email)
        var list = mutableListOf<String>()
        var stringValue = ""

        ref.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    stringValue = document.get("office_hours_list") as String
                    Log.d(TAG, "office_hours_list successfully read")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        list = stringValue.split(",").map { it.trim() }.toMutableList()

        return list
    }

    fun readStudentsTimeInstance(email: String) : StudentsTimeInstance{
        var title = ""
        var time = ""
        var message = ""
        var status = ""
        var officeHoursCode = ""
        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("Student_Request")
            .whereEqualTo("email", email)
        ref.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    title = document.get("Title") as String
                    time = document.get("Time") as String
                    message = document.get("Message") as String
                    status = document.get("Status") as String
                    officeHoursCode = document.get("office_hours_code") as String

                    Log.d(TAG, " Time instance successfully read")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        return StudentsTimeInstance(title, time, message, status, officeHoursCode)
    }

    fun writeStudentsTimeInstance(title: String, time: String, message: String, status: String, officeHoursCode: String) {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Student_Request")

        val newInstance = hashMapOf(
            "Title" to title,
            "Time" to time,
            "Message" to message,
            "Status" to status,
            "office_hours_code" to officeHoursCode
        )

        myRef.add(newInstance)
            .addOnSuccessListener { Log.d(TAG, "Instance successfully added") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing Instance", e) }

    }



    fun readCourseById(courseId: String): MutableList<Any> {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Course").document(courseId)
        var list = mutableListOf<Any>()
        var nameSurname = ""
        var timeFrom = ""
        var timeTo = ""
        var weeksCount = 0

        myRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nameSurname = document.get("name_surname") as String
                    timeFrom = document.get("time_from") as String
                    timeTo = document.get("time_to") as String
                    weeksCount = document.get("weeks_count") as Int
                    list.add(Course(courseId, nameSurname, timeFrom, timeTo, weeksCount))
                    Log.d(TAG, "Name and surname successfully read")
                } else {
                    Log.d(TAG, "Is empty")
                }
            }
            .addOnFailureListener { exception ->
                if (exception is FirebaseFirestoreException) {
                    Log.e(TAG, "Error getting document: ", exception)
                }
            }
        return list
    }

    fun updateUsersPassword(password: String, email: String){
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Users").document(email)

        myRef.update("pass", password)
            .addOnSuccessListener { Log.d(TAG, "Password successfully updated") }
            .addOnFailureListener { e -> Log.w(TAG, "Error in updating a password", e) }
    }
}