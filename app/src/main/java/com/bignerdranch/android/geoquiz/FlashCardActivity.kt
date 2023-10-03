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
//

    // Inside the generateProblems() function
    private fun generateProblems() {
        // Generate 10 random problems with approximately half addition and half subtraction
        val problems = generateRandomProblems(10)
        displayProblems(problems)

        // Enable the Generate button after generating problems
        generateButton.isEnabled = true
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

    private fun displayProblems(problems: List<String>) {
        val message = problems.joinToString("\n")
        binding.flashcardTextView.text = message
    }

    // Add a method to calculate the score
    private fun calculateScore() {
        // Dummy calculation: half the problems correct
        score = Random.Default.nextInt(0, 11) * 2
        Toast.makeText(this, "Score: $score out of 10", Toast.LENGTH_SHORT).show()
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
