package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    private lateinit var resultsTV: TextView
    private var firstNumber = ""
    private var secondNumber = ""
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resultsTV = findViewById(R.id.resultsTV)
    }

    fun numberAction(view: View)
    {
        if (view is Button)
        {
            if (operator.isEmpty())
            {
                firstNumber += view.text.toString()
                resultsTV.text = firstNumber
            }
            else
            {
                secondNumber += view.text.toString()
                resultsTV.text = secondNumber
            }
        }
    }

    fun operationAction(view: View)
    {
        if (view is Button)
        {
            if (   secondNumber.isEmpty()
                && firstNumber.isNotEmpty()
                && firstNumber.last().isDigit())
            {
                operator = view.text.toString()
            }
        }

    }

    fun equalsAction(view: View)
    {
        if (   secondNumber.isNotEmpty()
            && secondNumber.toDoubleOrNull() != null
            && firstNumber.isNotEmpty()
            && firstNumber.toDoubleOrNull() != null)
        {
            var tmpResult = 0.0
            when(operator)
            {
                "+" -> {
                    tmpResult = firstNumber.toDouble() + secondNumber.toDouble()
                }
                "-" -> {
                    tmpResult = firstNumber.toDouble() - secondNumber.toDouble()
                }
                "*" -> {
                    tmpResult = firstNumber.toDouble() * secondNumber.toDouble()
                }
                "÷" -> {
                    if (secondNumber.toDouble() != 0.0)
                    {
                        tmpResult = firstNumber.toDouble() / secondNumber.toDouble()
                    }
                    else
                    {
                        Toast.makeText(this, "Nie można dzielić przez 0", Toast.LENGTH_SHORT).show()
                        return
                    }
                }
            }

            firstNumber = tmpResult.toString()
            secondNumber = ""
            operator = ""
            resultsTV.text = firstNumber
        }
    }

    fun backSpaceAction(view: View)
    {
        if (secondNumber.isNotEmpty())
        {
            secondNumber = secondNumber.dropLast(1)
            resultsTV.text = secondNumber
        }
        else if (operator.isNotEmpty())
        {
            operator = ""
        }
        else if (firstNumber.isNotEmpty())
        {
            firstNumber = firstNumber.dropLast(1)
            resultsTV.text = firstNumber
        }

        Toast.makeText(this, "Nie ma nic do usunięcia", Toast.LENGTH_SHORT).show()
    }

    fun allClearAction(view: View)
    {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        resultsTV.text=""
    }

    fun changeSignAction(view: View)
    {
        if (secondNumber.isNotEmpty())
        {
            secondNumber = if (secondNumber.first().isDigit()) {
                "-$secondNumber"
            } else {
                secondNumber.drop(1)
            }
            resultsTV.text = secondNumber
        }
        else if (firstNumber.isNotEmpty())
        {
            firstNumber = if (firstNumber.first().isDigit()) {
                "-$firstNumber"
            } else {
                firstNumber.drop(1)
            }
            resultsTV.text = firstNumber
        }
    }
    fun addPointAction(view: View)
    {
        if (   secondNumber.isNotEmpty()
            && !secondNumber.contains("."))
        {
            secondNumber += "."
            resultsTV.text = secondNumber
        }
        else if (   operator.isEmpty()
                 && firstNumber.isNotEmpty()
                 && !firstNumber.contains("."))
        {
            firstNumber += "."
            resultsTV.text = firstNumber
        }
    }
}



