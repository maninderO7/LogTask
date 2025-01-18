package com.o7.logtask

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DatePicker : AppCompatActivity() {

    val dateFormats = arrayOf(
    "MM/DD/YYYY",
    "DD/MM/YYYY",
    "YYYY/MM/DD",
    "DD-MM-YYYY",
    "YYYY-MM-DD",
    "DD.MM.YYYY",
    "MMMM DD, YYYY",
    "DD MMMM YYYY"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_date_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        findViewById<Button>(R.id.btnPickDate).setOnClickListener {

            DatePickerDialog(this, { _, year, month, day ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, day)

                var result = ""

                for (d in dateFormats){
                    result += d + " - " + SimpleDateFormat(d, Locale.getDefault()).format(selectedDate.time) + "\n"
                }

                findViewById<TextView>(R.id.tvResultDatePicker).text = result

            }, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).show()



        }

    }
}