package com.example.calculadora_imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val textViewCategory = findViewById<TextView>(R.id.textViewCategory)
        val buttonBack = findViewById<Button>(R.id.buttonBack)


        val bmi = intent.getFloatExtra("IMC_VALUE", 0.0f)
        val category = intent.getStringExtra("IMC_CATEGORY")

        textViewResult.text = String.format("IMC: %.2f", bmi)
        textViewCategory.text = category

        buttonBack.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }
    }
}