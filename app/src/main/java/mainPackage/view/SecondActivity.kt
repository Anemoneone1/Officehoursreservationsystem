package mainPackage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ScrollView
import com.example.officehoursreservationsystem.R
import mainPackage.model.OfficeHoursInstance

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val secondButAct = findViewById<ImageButton>(R.id.image_button_2)
        secondButAct.setOnClickListener{
            val Intent = Intent(this, Main2Activity::class.java)
            startActivity(Intent)
        }

        val myLinearLayout = findViewById<LinearLayout>(R.id.my_linear_layout)
        val myScrollView = findViewById<ScrollView>(R.id.scrollView3)
        val myList = listOf(OfficeHoursInstance("maksym@gmail.com", "10:00", "11:00", "1231"), OfficeHoursInstance("jane@gmail.com", "11:00", "12:00", "1231"))

        for (item in myList) {
            val button = Button(this)
            button.text = "${item.email} ${item.timeFrom} ${item.timeTo}"
            myLinearLayout.addView(button)
        }
        myScrollView.addView(myLinearLayout)
    }
}