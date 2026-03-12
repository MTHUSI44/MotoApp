package com.example.motoapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        } //ViewCompat
        // Code goes here vvv

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val etCarMake = findViewById<EditText>(R.id.etCarMake)
        val etCarModel = findViewById<EditText>(R.id.etCarModel)
        val etYear = findViewById<EditText>(R.id.etYear)
        val etMileage = findViewById<EditText>(R.id.etMileage)
        val btnProcess = findViewById<Button>(R.id.btnProcess)

// 2. Setting a click listener for the button (Must NOT be purple)
        btnProcess.setOnClickListener {
            val make = etCarMake.text.toString().trim()
            val model = etCarModel.text.toString().trim()
            val year = etYear.text.toString().trim()
            val mileage = etMileage.text.toString().trim()

            // 3. Proper Validation: Ensure all info is captured
            when {
                make.isEmpty() -> etCarMake.error = "Please enter the car make"
                model.isEmpty() -> etCarModel.error = "Please enter the car model"
                year.isEmpty() -> etYear.error = "Please enter the year"
                mileage.isEmpty() -> etMileage.error = "Please enter the mileage"
                else -> {
                    // Success! Process the information as requested
                    val summary = "Captured: $year $make $model with $mileage km"
                    Toast.makeText(this, summary, Toast.LENGTH_LONG).show()

                    // Optional: Clear fields to make the UI easy to use again
                    clearForm(etCarMake, etCarModel, etYear, etMileage)
                }
            }
        }
    }

    private fun clearForm(vararg editTexts: EditText) {
        for (field in editTexts) {
            field.text.clear()
        }
        editTexts[0].requestFocus()

    }
}