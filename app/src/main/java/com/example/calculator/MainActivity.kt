package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentNumber: String = "0"

    @SuppressLint("SetTextI18n")
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

        val calculator = Calculator()

        val buttonNumbers = listOf(
            binding.btn0,
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )

        val buttonOperations = mapOf(
            binding.btnAdd to "+",
            binding.btnDiv to "/",
            binding.btnMul to "*",
            binding.btnSub to "-"
        )

        buttonNumbers.forEachIndexed { index, button ->
            button.setOnClickListener {
                if (currentNumber == "0") {
                    currentNumber = index.toString()
                } else {
                    currentNumber += index.toString()
                }
                binding.tvResult.text = currentNumber
            }
        }

        buttonOperations.forEach { operation ->
            operation.key.setOnClickListener {
                if ((operation.value != "/") || (currentNumber != "0")) {
                    calculator.addElement(currentNumber.toDouble())
                    calculator.addOperation(operation.value)
                    currentNumber = "0"
                    binding.tvResult.text = currentNumber
                } else {
                    Toast.makeText(this, "Zero division", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnPoint.setOnClickListener {
            if (currentNumber.contains('.')) {
                Toast.makeText(this, "Incorrect number", Toast.LENGTH_SHORT).show()
            } else {
                currentNumber += "."
                binding.tvResult.text = currentNumber
            }
        }

        binding.btnClear.setOnClickListener {
            currentNumber = "0"
            binding.tvResult.text = currentNumber
        }

        binding.btnResult.setOnClickListener {
            calculator.addElement(currentNumber.toDouble())
            val result = calculator.getResult()
            currentNumber = result.toString()
            binding.tvResult.text = currentNumber
        }
    }
}