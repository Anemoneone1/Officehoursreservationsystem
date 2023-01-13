package mainPackage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.officehoursreservationsystem.R

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val secondButAct = findViewById<ImageButton>(R.id.image_button_2)
        secondButAct.setOnClickListener{
            val Intent = Intent(this, Main2Activity::class.java)
            startActivity(Intent)
        }
    }
}