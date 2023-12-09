package com.example.calculator

import android.os.Bundle

class AdvancedCalcActivity : BaseCalcActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_advanced_calc)
        setResultView(findViewById(R.id.resultsTV))
    }
}