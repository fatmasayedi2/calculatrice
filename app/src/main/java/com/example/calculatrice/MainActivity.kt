package com.example.calculatrice

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var tvFormule: TextView
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvFormule = findViewById(R.id.tvFormule)
        tvResult = findViewById(R.id.tvResult)
        val clearButton = findViewById<MaterialButton>(R.id.clear)
        val plusButton = findViewById<MaterialButton>(R.id.plus)
        val divideButton = findViewById<MaterialButton>(R.id.devide)
        val sevenButton = findViewById<MaterialButton>(R.id.seven)
        val eightButton = findViewById<MaterialButton>(R.id.eight)
        val nineButton = findViewById<MaterialButton>(R.id.nine)
        val multiplyButton = findViewById<MaterialButton>(R.id.multiply)
        val fourButton = findViewById<MaterialButton>(R.id.four)
        val fiveButton = findViewById<MaterialButton>(R.id.five)
        val sixButton = findViewById<MaterialButton>(R.id.six)
        val minesButton = findViewById<MaterialButton>(R.id.mines)
        val oneButton = findViewById<MaterialButton>(R.id.one)
        val twoButton = findViewById<MaterialButton>(R.id.two)
        val threeButton = findViewById<MaterialButton>(R.id.three)
        val equalButton = findViewById<MaterialButton>(R.id.equal)
        val zeroButton = findViewById<MaterialButton>(R.id.zero)
        val dotButton = findViewById<MaterialButton>(R.id.dot)

        clearButton.setOnClickListener {
            tvFormule.text = ""
            tvResult.text = "0"
        }


        plusButton.setOnClickListener {
            val currentFormule = tvFormule.text.toString()
            val currentValue = tvResult.text.toString()

            if (currentValue.isNotEmpty()) {
                val updatedFormule = "$currentFormule $currentValue +"
                tvFormule.text = updatedFormule
                tvResult.text = "0"
            }

        }

        divideButton.setOnClickListener {

            val currentFormule = tvFormule.text.toString()
            val currentValue = tvResult.text.toString()


            if (currentValue.isNotEmpty()) {
                val updatedFormule = "$currentFormule $currentValue /"
                tvFormule.text = updatedFormule
                tvResult.text = "0"
            }
        }

        sevenButton.setOnClickListener {

            appendDigit("7")
        }

        eightButton.setOnClickListener {

            appendDigit("8")
        }

        nineButton.setOnClickListener {
            appendDigit("9")
        }

        multiplyButton.setOnClickListener {
            handleOperator("*")
        }

        fourButton.setOnClickListener {
            appendDigit("4")
        }

        fiveButton.setOnClickListener {
            appendDigit("5")
        }

        sixButton.setOnClickListener {

            appendDigit("6")
        }

        minesButton.setOnClickListener {
            handleOperator("-")
        }

        oneButton.setOnClickListener {
            appendDigit("1")
        }

        twoButton.setOnClickListener {
            appendDigit("2")
        }
        threeButton.setOnClickListener {
            appendDigit("3")
        }

        zeroButton.setOnClickListener {
            appendDigit("0")
        }

        dotButton.setOnClickListener {
            appendDot()
        }

        equalButton.setOnClickListener {
            performCalculation()
        }
    }

    private fun appendDigit(digit: String) {
        val currentValue = tvResult.text.toString()

        // Vérifiez si la valeur actuelle est "0" pour la remplacer ou ajouter le chiffre
        val updatedValue = if (currentValue == "0") digit else "$currentValue$digit"
        tvResult.text = updatedValue
    }

    private fun appendDot() {
        val currentValue = tvResult.text.toString()


        if (!currentValue.contains('.')) {
            val updatedValue = "$currentValue."
            tvResult.text = updatedValue
        }
    }

    private fun handleOperator(operator: String) {

        val currentFormule = tvFormule.text.toString()

        val currentValue = tvResult.text.toString()


        if (currentValue.isNotEmpty()) {

            val updatedFormule = "$currentFormule $currentValue $operator"

            tvFormule.text = updatedFormule

            tvResult.text = "0"
        }
    }

        private fun performCalculation() {
            val currentFormule = tvFormule.text.toString()
            val currentValue = tvResult.text.toString()

            if (currentValue.isNotEmpty()) {
                val expression = currentFormule + currentValue
                try {
                    val result = ExpressionBuilder(expression).build().evaluate()
                    val formattedResult = formatResult(result)
                    tvResult.text = formattedResult
                    tvFormule.text = ""
                } catch (e: ArithmeticException) {
                    Log.e("CalculationError", "Error: ${e.message}")
                }
            }
        }
    private fun formatResult(result: Double): String {
        return String.format("%.2f", result)
    }


    }


