package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.kgNumber)
        val height = findViewById<EditText>(R.id.cmNumber)
        val calButton = findViewById<Button>(R.id.calculateButton)

        calButton.setOnClickListener {
            val displayBMI = findViewById<TextView>(R.id.bmiValue)
            val displayMessage = findViewById<TextView>(R.id.outputMessage)
            val outputCard = findViewById<CardView>(R.id.resultCard)

            if (weight.text.toString().toInt() == 0 || height.text.toString().toInt() == 0) {
                outputCard.visibility = INVISIBLE
                Toast.makeText(this, "Enter a Valid input!!", Toast.LENGTH_SHORT).show()
            }
            else {
                val bmiValue = weight.text.toString().toFloat() / ((height.text.toString().toFloat() / 100) * (height.text.toString().toFloat() / 100))
                val bmiTwoDigits = String.format("%.2f", bmiValue).toFloat()

                displayBMI.text = bmiTwoDigits.toString()

                var resultText = ""
                when {
                    bmiTwoDigits < 18.5 -> {
                        resultText = "Underweight"
                    }
                    bmiTwoDigits in 18.5 .. 24.99 -> {
                        resultText = "Healthy"
                    }
                    bmiTwoDigits in 25.0 .. 29.99 -> {
                        resultText = "Overweight"
                    }
                    bmiTwoDigits > 29.99 -> {
                        resultText = "Obese"
                    }
                }
                displayMessage.text = resultText

                outputCard.visibility = VISIBLE
            }
        }

    }
}