package com.example.calculatorjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var display: EditText
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        display = findViewById(R.id.display)
    }

    fun onButtonClick(view: View) {
        val button = view as Button
        val buttonText = button.text.toString()

        when {
            buttonText == "C" -> {
                expression = ""
                display.setText("")
            }
            buttonText == "=" -> {
                try {
                    val result = evaluateExpression(expression)
                    display.setText(result.toString())
                    expression = result.toString()
                } catch (e: Exception) {
                    display.setText(e.message.toString())
                    expression = ""
                }
            }
            else -> {
                expression += buttonText
                display.append(buttonText)
            }
        }
    }

    private fun evaluateExpression(expression: String): Double {
        try {
            var result: Double

            if (expression.contains("+")) {
                val operands = expression.split("+")
                result = operands[0].toDouble() + operands[1].toDouble()
            } else if (expression.contains("-")) {
                val operands = expression.split("-")
                result = operands[0].toDouble() - operands[1].toDouble()
            } else if (expression.contains("*")) {
                val operands = expression.split("*")
                result = operands[0].toDouble() * operands[1].toDouble()
            } else if (expression.contains("/")) {
                val operands = expression.split("/")
                if (operands[1].toDouble() == 0.0) {
                    throw ArithmeticException("Division by zero")
                }
                result = operands[0].toDouble() / operands[1].toDouble()
            } else {
                return expression.toDouble()
            }
             return result


        } catch (e: Exception) {
            throw ArithmeticException("Invalid expression")
        }
    }


}
