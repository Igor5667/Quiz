package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextName: EditText = findViewById(R.id.edit_text_name)
        val btnStart: Button = findViewById(R.id.start)

        //zapamiętanie poprzednej nazwy
        val playerName = intent.getStringExtra("playerName")
        if (playerName != null){
            editTextName.setText("$playerName")
        }

        btnStart.setOnClickListener {
            if(editTextName.text.isEmpty()){
                Toast.makeText(this, "Nie podano imienia", Toast.LENGTH_SHORT).show()
            }
            else if(editTextName.text.length < 3){
                Toast.makeText(this, "Za ktrótkie Imie", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra("playerName", editTextName.text.toString())
                startActivity(intent)
                finish()
            }
        }
    }
}