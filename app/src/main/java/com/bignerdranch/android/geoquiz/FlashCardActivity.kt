package com.bignerdranch.android.geoquiz

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityFlashcardBinding
import kotlin.random.Random

class FlashCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlashcardBinding
    private lateinit var generateButton: Button
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateButton = binding.generateFlashcardsButton
        generateButton.setOnClickListener { generateProblems() }

        // Initialize flashcards with a default message
        binding.flashcardTextView.text = "Press 'Generate 10 Random Problems'"

        generateButton.isEnabled = true

        // Set an onClickListener for the Generate button
        generateButton.setOnClickListener { generateProblems() }

        // Get the username from the intent
        val username = intent.getStringExtra("USERNAME")
        username?.let {
            Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
        }
    }
    private var currentProblemIndex: Int = 0
    private val problems = mutableListOf<String>()
    private var userAnswers = mutableListOf<Int>()
    // Inside the generateProblems() function
    private fun generateProblems() {
        // Reset variables for a new game
        currentProblemIndex = 0
        problems.clear()
        userAnswers.clear()

        // Generate 10 random problems with approximately half addition and half subtraction
        problems.addAll(generateRandomProblems(10))

        // Display the first problem
        displayNextProblem()
    }


    private fun generateRandomProblems(count: Int): List<String> {
        val problems = mutableListOf<String>()
        val random = Random.Default

        for (i in 1..count) {
            val operand1 = random.nextInt(1, 100)
            val operand2 = random.nextInt(1, 21)
            val isAddition = random.nextBoolean()

            val problem = if (isAddition) {
                "$operand1 + $operand2 = ?"
            } else {
                "$operand1 - $operand2 = ?"
            }

            problems.add(problem)
        }

        return problems
    }

    // Display the next problem
    private fun displayNextProblem() {
        if (currentProblemIndex < problems.size) {
            binding.flashcardTextView.text = problems[currentProblemIndex]
            currentProblemIndex++
        } else {
            // All problems answered, calculate the score and display a Toast
            calculateScore()
        }
    }

    // Add a method to calculate the score
    private fun calculateScore() {
        var correctAnswers = 0

        // Calculate correct answers
        for (i in 0 until problems.size) {
            val answer = userAnswers.getOrNull(i)
            if (answer != null && evaluateAnswer(problems[i], answer)) {
                correctAnswers++
            }
        }

        val scoreMessage = "Score: $correctAnswers out of ${problems.size}"
        Toast.makeText(this, scoreMessage, Toast.LENGTH_SHORT).show()

        // Reset the game for a new round
        generateButton.isEnabled = true
        generateButton.text = getString(R.string.generate_button_text)
    }

    // Evaluate the answer for a problem
    private fun evaluateAnswer(problem: String, userAnswer: Int): Boolean {
        val regex = "(\\d+) ([+-]) (\\d+) = \\?".toRegex()
        val matchResult = regex.find(problem)

        if (matchResult != null) {
            val (operand1, operator, operand2) = matchResult.destructured
            return when (operator) {
                "+" -> operand1.toInt() + operand2.toInt() == userAnswer
                "-" -> operand1.toInt() - operand2.toInt() == userAnswer
                else -> false
            }
        }
        return false
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("SCORE", score)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        score = savedInstanceState.getInt("SCORE", 0)
    }
}
