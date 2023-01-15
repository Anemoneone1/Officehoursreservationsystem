package mainPackage.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.officehoursreservationsystem.R
import mainPackage.utils.utils1.timeFormCheck
import mainPackage.viewModel.OHRViewModel

class RequestCreationActivity(val code: String) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_creation)

        val currCode = intent.getStringExtra("ITEM_ID", -1)
        var viewModel = ViewModelProvider(this).get(OHRViewModel::class.java)

        val returnButton = findViewById<ImageButton>(R.id.image_button_3)
        returnButton.id = R.id.image_button_3
        val applyButton = findViewById<Button>(R.id.apply_button)
        applyButton.id = R.id.apply_button
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        cancelButton.id = R.id.cancel_button
        val buttonList = arrayListOf(applyButton, cancelButton)

        for (button in buttonList) {
            val onClickListener = button.setOnClickListener {
                when (it.id) {
                    R.id.apply_button -> {
                        // code for apply_button
                    }
                    R.id.cancel_button -> {
                        onButtonClickReturn(it)
                    }
                    R.id.image_button_3 ->{
                        onButtonClickReturn(it)
                    }
                }
            }
        }

        fun onButtonClickApply(view: View){
            val timeInput = findViewById<EditText>(R.id.time_input)
            val titleInput = findViewById<EditText>(R.id.title_input)
            val textInput = findViewById<EditText>(R.id.message_input)
            val time = timeInput.text.toString().trim()
            val title = titleInput.text.toString().trim()
            val text = textInput.text.toString().trim()
            if(time.isEmpty() || title.isEmpty() || text.isEmpty()){
                showEmptyFieldsPopup(this)
            }
            else if (!timeFormCheck(time)){
                showIncorrectTimePopup(this)
            }
            else if (viewModel.timeOutOfBoundsCheck(currCode!!, time)){
                showTimeOutOfBoundsPopup(this)
            }
            else{

            }
        }
    }

    fun showEmptyFieldsPopup(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("Please fill all the fields before applying")
        builder.setPositiveButton("OK") { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showTimeOutOfBoundsPopup(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("You cannot set the time outside of office hours")
        builder.setPositiveButton("OK") { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun showIncorrectTimePopup(context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
        builder.setMessage("The time is in incorrect form")
        builder.setPositiveButton("OK") { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    fun onButtonClickReturn(view: View){
        val intent = Intent(this, OfficeHoursListActivity::class.java)
        startActivity(intent)
    }
}