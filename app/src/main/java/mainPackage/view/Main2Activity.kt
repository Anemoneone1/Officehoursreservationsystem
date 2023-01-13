package mainPackage.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.officehoursreservationsystem.R

class Main2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val secondButAct = findViewById<Button>(R.id.btn_login)
        secondButAct.setOnClickListener{
            val Intent = Intent(this, SecondActivity::class.java)
            startActivity(Intent)
        }
    }

}