package com.bferrari.stonechallenge

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bferrari.stonechallenge.Matchers.Companion.checkForDuplicatedEntries
import com.bferrari.stonechallenge.ui.searchfacts.SearchFactsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SearchFactsTest {

    @get:Rule
    var rule: ActivityTestRule<SearchFactsActivity> = ActivityTestRule(SearchFactsActivity::class.java)

    @Before
    fun init() {
        ActivityScenario.launch(SearchFactsActivity::class.java)
    }

    @Test
    fun pastSearchesCheckDuplicatedEntries() {
        onView(withId(R.id.pastSearchesRv)).check(matches(isDisplayed()))
        onView(withId(R.id.pastSearchesRv)).check(matches(checkForDuplicatedEntries()))
    }

    @Test
    fun checkCategories() {
        onView(withId(R.id.suggestionTags)).check(matches(isDisplayed()))
        onView(withId(R.id.suggestionTags)).check(matches(hasChildCount(8)))
    }

    @Test
    fun checkSearch() {
        onView(withId(R.id.searchEditText)).check(matches(isDisplayed()))
        onView(withId(R.id.searchEditText)).perform(ViewActions.typeText("test"),
            ViewActions.pressImeActionButton())
    }
}