package com.example.calculator

import android.util.Log
import java.util.LinkedList
import java.util.Queue


class Calculator {

    private var numbers: Queue<Double> = LinkedList()
    private var operations: Queue<String> = LinkedList()

    fun addOperation(operation: String) {
        operations.add(operation)
    }

    fun addElement(element: Double) {
        numbers.add(element)
    }

    fun getResult(): Double {

        operations.forEach {
            Log.d("operation", it)
        }

        numbers.forEach {
            Log.d("number", it.toString())
        }

        var result = numbers.remove()

        while (operations.isNotEmpty()) {
            val operation = operations.remove()
            val number1 = numbers.remove()

            result = when (operation) {
                "+" -> {
                    result + number1
                }

                "-" -> {
                    result - number1
                }

                "*" -> {
                    result * number1
                }

                "/" -> {
                    result / number1
                }

                else -> {
                    0.0
                }
            }
        }

        numbers.clear()
        operations.clear()

        return result
    }
}