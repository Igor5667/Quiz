package com.example.quiz

object Constants {
    
    fun getQuestions() : ArrayList<Question>{

        val questionsList = ArrayList<Question>();

        val questionOne = Question(
            1, "Stolica Polski?", R.drawable.flag_pl,
            optionOne = "Kraków",
            optionTwo = "Gdańsk",
            optionThree = "Warszawa",
            optionFour = "Lwów",
            3
        )

        val questionTwo = Question(
            2, "Stolica Niemiec?", R.drawable.flag_de,
            optionOne = "Pekin",
            optionTwo = "Berlin",
            optionThree = "Hamburg",
            optionFour = "Frankfurt",
            2
        )

        val questionThree = Question(
            3, "Stolica Francji?", R.drawable.flag_fr,
            optionOne = "Paryż",
            optionTwo = "Lyon",
            optionThree = "Marsylia",
            optionFour = "Nicea",
            1
        )

        val questionFour = Question(
            4, "Stolica Włoch?", R.drawable.flag_it,
            optionOne = "Florencja",
            optionTwo = "Mediolan",
            optionThree = "Neapol",
            optionFour = "Rzym",
            4
        )

        val questionFive = Question(
            5, "Stolica Hiszpanii?", R.drawable.flag_es,
            optionOne = "Walencja",
            optionTwo = "Barcelona",
            optionThree = "Madryt",
            optionFour = "Sewilla",
            3
        )

        val questionSix = Question(
            6, "Stolica Szwajcarii?", R.drawable.flag_ch,
            optionOne = "Berlin",
            optionTwo = "Manchester",
            optionThree = "Liverpool",
            optionFour = "Berno",
            4
        )

        val questionSeven = Question(
            7, "Stolica Holandii?", R.drawable.flag_nl,
            optionOne = "Rotterdam",
            optionTwo = "Amsterdam",
            optionThree = "Haga",
            optionFour = "Utrecht",
            2
        )

        val questionEight = Question(
            8, "Stolica Szwecji?", R.drawable.flag_se,
            optionOne = "Sztokholm",
            optionTwo = "Göteborg",
            optionThree = "Malmö",
            optionFour = "Uppsala",
            1
        )


        questionsList.add(questionOne)
        questionsList.add(questionTwo)
        questionsList.add(questionThree)
        questionsList.add(questionFour)
        questionsList.add(questionFive)
        questionsList.add(questionSix)
        questionsList.add(questionSeven)
        questionsList.add(questionEight)

        return questionsList
    }
}