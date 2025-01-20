package com.o7.logtask

import android.app.TimePickerDialog
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

class MyTimePicker : AppCompatActivity() {

    val timeFormats = arrayOf(
        "HH:mm:ss",
        "hh:mm:ss a",
        "HH:mm",
        "hh:mm a",
        "HH:mm:ss.SSS",
        "hh:mm:ss.SSS a"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        findViewById<Button>(R.id.btnPickTime).setOnClickListener {

            TimePickerDialog(this, {_, hour, minute ->

                val selectedTime = Calendar.getInstance()
                selectedTime.set(Calendar.HOUR_OF_DAY, hour)
                selectedTime.set(Calendar.MINUTE, minute)
              //  selectedTime.set(Calendar.SECOND, second)


                var result = ""

                for (d in timeFormats){
                    result += d + " - " + SimpleDateFormat(d, Locale.getDefault()).format(selectedTime.time) + "\n"
                }

                findViewById<TextView>(R.id.tvResultTimePicker).text = result

            }, Calendar.getInstance().get(Calendar.HOUR),
                Calendar.getInstance().get(Calendar.MINUTE),
                true).show()

        }
    }
}