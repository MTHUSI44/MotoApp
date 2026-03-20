package com.example.motoapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
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

                val make = findViewById<EditText>(R.id.edtMake)
                val model = findViewById<EditText>(R.id.edtModel)
                val year = findViewById<EditText>(R.id.edtYear)
                val mileage = findViewById<EditText>(R.id.edtMileage)
                val btnSave = findViewById<Button>(R.id.btnSave)

                btnSave.setOnClickListener {

                    val makeText = make.text.toString()
                    val modelText = model.text.toString()
                    val yearText = year.text.toString()
                    val mileageText = mileage.text.toString()

                    //  Empty validation
                    if (makeText.isEmpty() || modelText.isEmpty() || yearText.isEmpty() || mileageText.isEmpty()) {
                        Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }

                    val yearInt = yearText.toInt()

                    //  Year validation
                    if (yearInt < 1900 || yearInt > 2026) {
                        year.error = "Enter a valid year (1900 - 2026)"
                        return@setOnClickListener
                    }

                    // Show AlertDialog
                    val message = """
                Make: $makeText
                Model: $modelText
                Year: $yearText
                Mileage: $mileageText KM
            """.trimIndent()

                    AlertDialog.Builder(this)
                        .setTitle("Confirm Car Details")
                        .setMessage(message)
                        .setPositiveButton("Save") { _, _ ->
                            Toast.makeText(this, "Saved Successfully!", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("Delete") { _, _ ->
                            make.text.clear()
                            model.text.clear()
                            year.text.clear()
                            mileage.text.clear()
                        }
                        .show()
                }
            }
        }