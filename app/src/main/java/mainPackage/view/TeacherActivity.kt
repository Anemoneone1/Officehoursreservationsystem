package mainPackage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.officehoursreservationsystem.R

class TeacherActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        val butAct = findViewById<ImageButton>(R.id.image_button_4)
        butAct.setOnClickListener {
            val Intent = Intent(this, OfficeHoursListActivity::class.java)
            startActivity(Intent)
        }
    }
}