package com.example.calculadora_imc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextHeight: EditText
    private lateinit var editTextWeight: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var buttonClear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextHeight = findViewById(R.id.editTextHeight)
        editTextWeight = findViewById(R.id.editTextWeight)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonClear = findViewById(R.id.buttonClear)

        buttonCalculate.setOnClickListener {
            val heightStr = editTextHeight.text.toString()
            val weightStr = editTextWeight.text.toString()

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                Toast.makeText(this, "Por favor, insira valores válidos.", Toast.LENGTH_SHORT).show()
            } else {
                val height = heightStr.toFloatOrNull()
                val weight = weightStr.toFloatOrNull()

                if (height != null && weight != null) {
                    val bmi = weight / (height * height)
                    val category = when {
                        bmi < 18.5 -> "Baixo peso"
                        bmi in 18.5..24.9 -> "Peso normal"
                        bmi in 25.0..29.9 -> "Sobrepeso"
                        else -> "Obesidade"
                    }


                    val intent = Intent(this, ResultActivity::class.java).apply {
                        putExtra("IMC_VALUE", bmi)
                        putExtra("IMC_CATEGORY", category)
                    }
                    startActivityForResult(intent, 1)
                } else {
                    Toast.makeText(this, "Por favor, insira valores numéricos.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonClear.setOnClickListener {
            clearFields()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            clearFields() // Limpa os campos ao retornar
        }
    }

    private fun clearFields() {
        editTextHeight.text.clear()
        editTextWeight.text.clear()
    }
}
