package com.example.calculator

import android.os.Bundle

class SimpleCalcActivity : BaseCalcActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_calc)
        setResultView(findViewById(R.id.resultsTV))
    }
}



