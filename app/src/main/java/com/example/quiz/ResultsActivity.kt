package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsActivity : AppCompatActivity() {

    private var playerName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.results_activity)

        val nicknameTextView = findViewById<TextView>(R.id.nickname)
        val pointsAmountTextView = findViewById<TextView>(R.id.points_amount)

        playerName = intent.getStringExtra("playerName")
        nicknameTextView.text = playerName

        //warunek małej ilości punktów i dużej
        val pointsAmount = intent.getStringExtra("pointsAmount")!!.toInt()
        if (pointsAmount > 6) {
            pointsAmountTextView.setTextColor(Color.GREEN)
        } else if (pointsAmount < 3) {
            pointsAmountTextView.setTextColor(Color.RED)
        }
        pointsAmountTextView.text = pointsAmount.toString()
    }

    fun onAgainClick(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("playerName", "$playerName")
        startActivity(intent)
        finish()
    }
}
