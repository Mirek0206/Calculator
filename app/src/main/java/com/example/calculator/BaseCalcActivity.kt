package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*

open class BaseCalcActivity : AppCompatActivity()
{
    private lateinit var resultsTV: TextView
    private var firstNumber = ""
    private var secondNumber = ""
    private var operator = ""

    private var resFirstNumber = ""
    private var resSecondNumber = ""
    private var resOperator = ""

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
            else if (   secondNumber.isNotEmpty()
                     && secondNumber.last().isDigit())
            {
                equalsAction(view)
                firstNumber = resFirstNumber
                operator = view.text.toString()

                resultsTV.text = firstNumber
            }
            else if (firstNumber.isEmpty())
            {
                firstNumber = if (resFirstNumber.isNotEmpty()) {
                    resFirstNumber
                } else {
                    "0"
                }

                operator = view.text.toString()
                resultsTV.text = firstNumber
            }
            else
            {
                Toast.makeText(this, "Nie można wybrać typu operacji.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun equalsAction(view: View)
    {
        if (firstNumber.isEmpty() && resFirstNumber.isNotEmpty())
        {
            firstNumber = resFirstNumber
            secondNumber = resSecondNumber
            operator = resOperator
        }
        else if (operator.isEmpty() && resOperator.isNotEmpty())
        {
            operator = resOperator
            secondNumber = resSecondNumber
        }
        else if (secondNumber.isEmpty())
        {
            secondNumber = firstNumber
        }


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
                "x^y" -> {
                    tmpResult = firstNumber.toDouble().pow(secondNumber.toDouble())
                }
            }

            resultsTV.text = tmpResult.toString()

            resFirstNumber = tmpResult.toString()
            resSecondNumber = secondNumber
            resOperator = operator

            firstNumber = ""
            secondNumber = ""
            operator = ""
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
        else if (resFirstNumber.isNotEmpty())
        {
            allClearAction(view)
        }
        else
        {
            Toast.makeText(this, "Nie masz nic do usunięcia", Toast.LENGTH_SHORT).show()
        }
    }

    fun allClearAction(view: View)
    {
        firstNumber = ""
        secondNumber = ""
        operator = ""
        resFirstNumber = ""
        resSecondNumber = ""
        resOperator = ""
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
            if (operator.isNotEmpty())
            {
                if (resFirstNumber.isNotEmpty())
                {
                    secondNumber = if (resFirstNumber.first().isDigit()) {
                        "-$secondNumber"
                    } else {
                        resFirstNumber.drop(1)
                    }
                }
                else
                {
                    secondNumber = if (firstNumber.first().isDigit()) {
                        "-$secondNumber"
                    } else {
                        firstNumber.drop(1)
                    }
                }

                resultsTV.text = secondNumber
            }
            else
            {
                firstNumber = if (firstNumber.first().isDigit()) {
                    "-$firstNumber"
                } else {
                    firstNumber.drop(1)
                }
                resultsTV.text = firstNumber
            }
        }
        else if (resFirstNumber.isNotEmpty())
        {
            firstNumber = if (resFirstNumber.first().isDigit()) {
                "-$resFirstNumber"
            } else {
                resFirstNumber.drop(1)
            }
            resultsTV.text = firstNumber
        }
    }

    fun addPointAction(view: View)
    {
        if (firstNumber.isEmpty())
        {
            firstNumber += "0."
            resultsTV.text = firstNumber
        }
        else if (   secondNumber.isNotEmpty()
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
        else if (operator.isNotEmpty() && secondNumber.isEmpty())
        {
            secondNumber = "0."
            resultsTV.text = secondNumber
        }
        else
        {
            Toast.makeText(this, "Nie można postawić kropki!", Toast.LENGTH_SHORT).show()
        }
    }

    fun trigonAction(view: View)
    {
        if (view is Button)
        {
            var trigonOp = view.text.toString()
            val op = view.text.toString()

            if (   secondNumber.isNotEmpty()
                && secondNumber.last().isDigit())
            {
                secondNumber = calcTrigonOp(secondNumber.toDouble(), op).toString()
                resultsTV.text = secondNumber
            }
            else if (   firstNumber.isNotEmpty()
                     && firstNumber.last().isDigit())
            {
                if (operator.isEmpty())
                {
                    firstNumber = calcTrigonOp(firstNumber.toDouble(), op).toString()
                    resultsTV.text = firstNumber
                }
                else
                {
                    secondNumber = calcTrigonOp(firstNumber.toDouble(), op).toString()
                    resultsTV.text = secondNumber
                }
            }
            else if (   firstNumber.isEmpty()
                     && secondNumber.isEmpty()
                     && resFirstNumber.isNotEmpty())
            {
                firstNumber = calcTrigonOp(resFirstNumber.toDouble(), op).toString()
                resultsTV.text = firstNumber
            }
            else
            {
                Toast.makeText(this, "Nie można wykonać operacji!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setResultView(result: TextView)
    {
        resultsTV = result
    }

    private fun calcTrigonOp(input: Double, operation: String): Double
    {
        var result = 0.0
        when(operation)
        {
            "sin" -> {
                result = sin(input)
            }
            "cos" -> {
                result = cos(input)
            }
            "tg" -> {
                result = tan(input)
            }
            "ln" -> {
                result = ln(input)
            }
            "log" -> {
                result = log10(input)
            }
            "sqrt" -> {
                result = sqrt(input)
            }
            "x^2" -> {
                result = input.pow(2.0)
            }
        }

        return result
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("firstNumber", firstNumber)
        outState.putString("secondNumber", secondNumber)
        outState.putString("operator", operator)
        outState.putString("resFirstNumber", resFirstNumber)
        outState.putString("resSecondNumber", resSecondNumber)
        outState.putString("resOperator", resOperator)
        outState.putString("resultsTV", resultsTV.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        firstNumber = savedInstanceState.getString("firstNumber", "")
        secondNumber = savedInstanceState.getString("secondNumber", "")
        operator = savedInstanceState.getString("operator", "")
        resFirstNumber = savedInstanceState.getString("resFirstNumber", "")
        resSecondNumber = savedInstanceState.getString("resSecondNumber", "")
        resOperator = savedInstanceState.getString("resOperator", "")
        resultsTV.text = savedInstanceState.getString("resultsTV", "")
    }
}