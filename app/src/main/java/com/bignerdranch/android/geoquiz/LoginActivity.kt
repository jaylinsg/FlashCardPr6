package com.bignerdranch.android.geoquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.geoquiz.databinding.ActivityMainBinding

private const val TAG = "LoginActivity"  // Changed TAG to reflect the activity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // Assuming you have a hardcoded username and password
    private val hardcodedUsername = "Username"
    private val hardcodedPassword = "Password"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.trueButton.setOnClickListener {
            // Check username and password
            val enteredUsername = binding.usernameEditText.text.toString()
            val enteredPassword = binding.passwordEditText.text.toString()
            if (enteredUsername == hardcodedUsername && enteredPassword == hardcodedPassword) {
                // Successful login
                displayWelcomeMessage(enteredUsername)
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayWelcomeMessage(username: String) {
        Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()

        // Create an intent to start the FlashCardActivity and pass the username
        val intent = Intent(this, FlashCardActivity::class.java).apply {
            putExtra("USERNAME", username)
        }

        // Start the FlashCardActivity
        startActivity(intent)

        // Finish the current activity (LoginActivity)
        finish()
    }

}

