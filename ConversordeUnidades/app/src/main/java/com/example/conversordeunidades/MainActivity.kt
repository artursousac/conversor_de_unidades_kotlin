package com.example.conversordeunidades
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinnerInput: Spinner = findViewById(R.id.spinner_input_unit)
        val spinnerOutput: Spinner = findViewById(R.id.spinner_output_unit)
        val inputValue: EditText = findViewById(R.id.input_value)
        val convertButton: Button = findViewById(R.id.convert_button)
        val resultText: TextView = findViewById(R.id.result_text)
        val casaDecimal: Spinner = findViewById(R.id.spinner_input_casa_decimal)

        convertButton.setOnClickListener {
            val value = inputValue.text.toString().toDoubleOrNull()
            if (value != null) {
                val inputUnit = spinnerInput.selectedItem.toString()
                val outputUnit = spinnerOutput.selectedItem.toString()
                val result = convertUnits(value, inputUnit, outputUnit)
                val inputCasaDecimal = casaDecimal.selectedItem.toString().toInt()
                val formatedResult = String.format("%.${inputCasaDecimal}f", result).replace(".", ",")
                resultText.text = "$value $inputUnit é igual a $formatedResult $outputUnit"
            } else {
                Toast.makeText(this, "Por favor, insira um valor válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun convertUnits(value: Double, inputUnit: String, outputUnit: String): Double {
        val baseValueInMeters = when (inputUnit) {
            "Centímetros" -> value / 100
            "Metros" -> value
            "Quilômetros" -> value * 1000
            "Milhas" -> value * 1609.34
            else -> 0.0
        }

        return when (outputUnit) {
            "Centímetros" -> baseValueInMeters * 100
            "Metros" -> baseValueInMeters
            "Quilômetros" -> baseValueInMeters / 1000
            "Milhas" -> baseValueInMeters / 1609.34
            else -> 0.0
        }
    }
}
