package com.example.quiz

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var questionsList: ArrayList<Question>? = null
    private var currentIndex: Int = 1
    private var selectedOptionPosition: Int = 1

    private var progressBar: ProgressBar? = null
    private var textviewProgressBar: TextView? = null

    private var textviewQuestion: TextView? = null
    private var textviewOptionOne: TextView? = null
    private var textviewOptionTwo: TextView? = null
    private var textviewOptionThree: TextView? = null
    private var textviewOptionFour: TextView? = null

    private var imageView: ImageView? = null
    private var buttonSubmit: Button? = null

    private var timerTextView: TextView? = null

    private var pointsAmount: Int = 8
    private var submision: Boolean = false
    private var currentTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        questionsList = Constants.getQuestions()

        progressBar = findViewById(R.id.progress_bar)
        textviewProgressBar = findViewById(R.id.textview_progress_bar)
        textviewQuestion = findViewById(R.id.textview_question)

        textviewOptionOne = findViewById(R.id.textview_option_one)
        textviewOptionTwo = findViewById(R.id.textview_option_two)
        textviewOptionThree = findViewById(R.id.textview_option_three)
        textviewOptionFour = findViewById(R.id.textview_option_four)

        imageView = findViewById(R.id.imageview_image)
        buttonSubmit = findViewById(R.id.btn_submit)

        timerTextView = findViewById<TextView>(R.id.timer)

        textviewOptionOne?.setOnClickListener(this)
        textviewOptionTwo?.setOnClickListener(this)
        textviewOptionThree?.setOnClickListener(this)
        textviewOptionFour?.setOnClickListener(this)
        buttonSubmit?.setOnClickListener(this)

        setQuestion()
        startTimer()
    }

    private fun setQuestion() {
        defaultOptionsView()

        val question: Question = questionsList!![currentIndex - 1]
        textviewQuestion?.text = question.question
        imageView?.setImageResource(question.image)
        progressBar?.progress = currentIndex
        textviewProgressBar?.text = "$currentIndex/${questionsList?.size}"

        textviewOptionOne?.text = question.optionOne
        textviewOptionTwo?.text = question.optionTwo
        textviewOptionThree?.text = question.optionThree
        textviewOptionFour?.text = question.optionFour

        buttonSubmit?.text = "Złóż odpowiedz"
        startTimer()
    }

    private fun startTimer() {
        currentTimer?.cancel()
        timerTextView?.setTextColor(Color.parseColor("#ffffff"))
        timerTextView?.setTextSize(15f)
        currentTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                timerTextView?.text = "$secondsLeft s"
                if (secondsLeft < 10) {
                    timerTextView?.setTextColor(Color.parseColor("#ff0000"))
                    timerTextView?.setTextSize(30f)
                }
            }

            override fun onFinish() {
                timerTextView?.text = "Koniec czasu"
                submision=true
                pointsAmount--
                buttonSubmit?.text = "Kolejne pytanie"
            }
        }
        currentTimer?.start()
    }



    private fun defaultOptionsView() {
        val options = ArrayList<TextView?>()

        textviewOptionOne?.let {
            options.add(0, it)
        }
        textviewOptionTwo?.let {
            options.add(1, it)
        }
        textviewOptionThree?.let {
            options.add(2, it)
        }
        textviewOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option?.setTextColor(Color.parseColor("#7a8089"))
            option?.typeface = Typeface.DEFAULT
            option?.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionsView(textview: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()

        selectedOptionPosition = selectedOptionNumber
        textview.setTextColor(Color.parseColor("#363a43"))
        textview.setTypeface(textview.typeface, Typeface.BOLD)
        textview.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_bg)
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> textviewOptionOne?.background = ContextCompat.getDrawable(this, drawableView)
            2 -> textviewOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)
            3 -> textviewOptionThree?.background = ContextCompat.getDrawable(this, drawableView)
            4 -> textviewOptionFour?.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.textview_option_one -> {
                if (submision) return
                textviewOptionOne?.let { selectedOptionsView(it, 1) }
            }
            R.id.textview_option_two -> {
                if (!submision) {
                    textviewOptionTwo?.let { selectedOptionsView(it, 2) }
                }
            }
            R.id.textview_option_three -> {
                if (!submision) {
                    textviewOptionThree?.let { selectedOptionsView(it, 3) }
                }
            }
            R.id.textview_option_four -> {
                if (!submision) {
                    textviewOptionFour?.let { selectedOptionsView(it, 4) }
                }
            }
            R.id.btn_submit -> {
                if (selectedOptionPosition == 0) {
                    currentIndex++
                    when {
                        currentIndex <= questionsList!!.size -> {
                            submision = false
                            setQuestion()
                        }
                        else -> {
                            val userName: String? = intent.getStringExtra("playerName")
                            val intent = Intent(this, ResultsActivity::class.java)
                            intent.putExtra("playerName", "$userName")
                            intent.putExtra("pointsAmount", "$pointsAmount")
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    val question = questionsList?.get(currentIndex - 1)
                    if (question!!.correctAnswer != selectedOptionPosition) {
                        answerView(selectedOptionPosition, R.drawable.wrong_option_border_bg)
                        pointsAmount-- // odejmowanie błędnych odpowiedzi
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (currentIndex == questionsList!!.size) {
                        buttonSubmit?.text = "Koniec"
                    } else {
                        buttonSubmit?.text = "Kolejne pytanie"
                    }
                    submision = true
                }
                selectedOptionPosition = 0
            }
        }
    }
}
