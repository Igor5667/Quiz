package com.example.quiz

object Constants {
    
    fun getQuestions() : ArrayList<Question>{

        val questionsList = ArrayList<Question>();

        val questionOne = Question(
            1, "Jakie to jest drzewo?", R.drawable.sosna,
            optionOne = "Brzoza",
            optionTwo = "sosna",
            optionThree = "wierzba",
            optionFour = "audi",
            2
        )


        val questionTwo = Question(
            2, "Co to jest za drzewo?", R.drawable.klon,
            optionOne = "dab",
            optionTwo = "sosna",
            optionThree = "kura",
            optionFour = "klon",
            4
        )

        val questionThree = Question(
            3, "Jaki to rodzaj drzewa?", R.drawable.wierzba,
            optionOne = "wierzba",
            optionTwo = "sosna",
            optionThree = "audi",
            optionFour = "klon",
            1
        )

        val questionFour = Question(
            4, "Czy wiesz jakie to drzewo?", R.drawable.dab,
            optionOne = "dab",
            optionTwo = "sosna",
            optionThree = "kura",
            optionFour = "klon",
            1
        )

        questionsList.add(questionOne)
        questionsList.add(questionTwo)
        questionsList.add(questionThree)
        questionsList.add(questionFour)

        return questionsList
    }
}