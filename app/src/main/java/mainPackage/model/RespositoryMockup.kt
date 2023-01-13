package mainPackage.model

import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestoreException

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

    fun writeOfficeHoursInstance(email: String, timeFrom: String, timeTo: String) {
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("OfficeHoursInstance")

        val newInstance = hashMapOf(
            "email" to email,
            "time_from" to timeFrom,
            "time_to" to timeTo
        )

        myRef.add(newInstance)
            .addOnSuccessListener { Log.d(TAG, "Instance successfully added") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing Instance", e) }

    }

    fun readOfficeHoursInstance(email: String) : MutableList<String>{
        var timeFrom = ""
        var timeTo = ""
        var userEmail = ""
        var list = mutableListOf<String>()
        val database = FirebaseFirestore.getInstance()
        val ref = database.collection("OfficeHoursInstance")
            .whereEqualTo("email", email)
            ref.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    timeFrom = document.get("time_from") as String
                    timeTo = document.get("time_to") as String
                    userEmail = document.get("email") as String
                    list.add(OfficeHoursInstance(userEmail, timeFrom, timeTo).toString())
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

    fun updateUsersPassword(password: String, email: String){
        val database = FirebaseFirestore.getInstance()
        val myRef = database.collection("Users").document(email)

        myRef.update("pass", password)
            .addOnSuccessListener { Log.d(TAG, "Password successfully updated") }
            .addOnFailureListener { e -> Log.w(TAG, "Error in updating a password", e) }
    }
}