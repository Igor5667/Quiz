package com.example.quiz

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("1234", "ResultsActivity została utworzona")

        val textView = findViewById<TextView>(R.id.text)
        Log.i("1234", "TextView: $textView")

    }
}