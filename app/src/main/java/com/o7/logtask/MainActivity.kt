package com.o7.logtask




import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.o7.logtask.databinding.ActivityMainBinding
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.floor


class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btnAction.setOnClickListener {

            val edt = EditText(this@MainActivity).apply {
                hint = "Enter a number"
                inputType = InputType.TYPE_CLASS_NUMBER
            }
            val dialog = AlertDialog.Builder(this).apply{
                setMessage("Do Something")
                setView(edt)

                setNeutralButton("Reset"){_, _ ->
                   binding.tvResult.text = "0"
                }

                setNegativeButton("Sub"){ _, _ ->
                    if (edt.text.isEmpty()){
                        Toast.makeText(this@MainActivity, "Dialog Box - Field is Empty", Toast.LENGTH_SHORT).show()
                    }

                    val num = edt.text.toString().toIntOrNull() ?: 0
                    binding.tvResult.text = ((binding.tvResult.text.toString().toIntOrNull()?:0) - num).toString().toEditable()


                }
                setPositiveButton("Add"){ _, _ ->
                    if (edt.text.isEmpty()){
                        Toast.makeText(this@MainActivity, "Dialog Box - Field is Empty", Toast.LENGTH_SHORT).show()
                    }

                    val num = edt.text.toString().toIntOrNull() ?: 0
                    binding.tvResult.text = ((binding.tvResult.text.toString().toIntOrNull()?: 0) + num).toString().toEditable()


                }

                setCancelable(false)
                show()
            }

        }


        binding.btnAdd.setOnClickListener {
            updateResult(getOldResult() + 2)
        }

        binding.btnSub.setOnClickListener {
            updateResult(getOldResult() - 2)
        }

        binding.btnMult.setOnClickListener {
            updateResult(getOldResult() * 2)
        }

        binding.btnDiv.setOnClickListener {
            updateResult(getOldResult() / 2)
        }



        binding.UITask.setOnClickListener {
            val intent = Intent(this, UiTask::class.java)
            startActivity(intent)
        }


        binding.btnDatePicker.setOnClickListener {
            val intent = Intent(this, DatePicker::class.java)
            startActivity(intent)
        }


        binding.btnTimePicker.setOnClickListener {
            val intent = Intent(this, com.o7.logtask.TimePicker::class.java)
            startActivity(intent)
        }


    }



    fun updateResult(value: Double) {
            binding.tvArithmaticAction.text = BigDecimal(value).setScale(2, RoundingMode.HALF_EVEN).toString()
    }

    fun getOldResult():Double{
        return binding.tvArithmaticAction.text.toString().toDoubleOrNull() ?: 0.0
    }



    private fun String.toEditable() : Editable =
        Editable.Factory.getInstance().newEditable(this)

    override fun onClick(dialog: DialogInterface?, which: Int) {
        Toast.makeText(this, "onclick pressed", Toast.LENGTH_SHORT).show()
    }

}