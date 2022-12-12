package mainPackage.model

import androidx.lifecycle.ViewModelProvider.NewInstanceFactory.Companion.instance
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class DatabaseMockup private constructor() {

    var Teacher = Teacher()
        private set

    companion object{
        @Volatile private var instance: DatabaseMockup? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance()= instance?: synchronized(this){
            instance?:DatabaseMockup().also { instance=it }
        }
    }
}