package com.bignerdranch.android.geoquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private lateinit var scenario: ActivityScenario<LoginActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    fun testLoginWithValidCredentials() {
        // Type the hardcoded username and password
        onView(withId(R.id.username_edit_text)).perform(replaceText("exampleUsername"))
        onView(withId(R.id.password_edit_text)).perform(replaceText("examplePassword"))

        // Click the login button
        onView(withId(R.id.true_button)).perform(click())

        // Check if the welcome message is displayed
        onView(withText("Welcome exampleUsername")).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginWithInvalidCredentials() {
        // Type incorrect username and password
        onView(withId(R.id.username_edit_text)).perform(replaceText("invalidUsername"))
        onView(withId(R.id.password_edit_text)).perform(replaceText("invalidPassword"))

        // Click the login button
        onView(withId(R.id.true_button)).perform(click())

        // Check if the error message is displayed
        onView(withText("Invalid username or password")).check(matches(isDisplayed()))
    }

    // Add more test cases to validate various aspects of LoginActivity
}
