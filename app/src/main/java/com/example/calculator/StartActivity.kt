package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<Button>(R.id.btn_readme).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        findViewById<Button>(R.id.btn_simple_calculator).setOnClickListener {
            startActivity(Intent(this, SimpleCalcActivity::class.java))
        }

        findViewById<Button>(R.id.btn_advanced_calculator).setOnClickListener {
            startActivity(Intent(this, AdvancedCalcActivity::class.java))
        }

        findViewById<Button>(R.id.btn_exit).setOnClickListener {
            finish()
        }
    }
}