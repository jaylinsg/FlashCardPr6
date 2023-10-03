package com.bignerdranch.android.geoquiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityFlashcardBinding

class FlashCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFlashcardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize flashcards and other setup logic here

        // Example of how to set a flashcard text
        binding.flashcardTextView.text = "Flashcard content goes here"
    }
}

