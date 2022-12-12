package mainPackage.model

data class Course(val code: String, val teacher: User, val name: String) {

    override fun toString(): String {
        return "$code - $name\n$teacher"
    }
}