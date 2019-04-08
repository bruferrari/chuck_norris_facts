package com.bferrari.stonechallenge

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.bferrari.stonechallenge.Matchers.Companion.checkTextSizeAt
import com.bferrari.stonechallenge.Matchers.Companion.checkUncategorizedLabel
import com.bferrari.stonechallenge.ui.factslist.FactsActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FactsListTest {

    @get:Rule
    val rule: ActivityTestRule<FactsActivity> = ActivityTestRule(FactsActivity::class.java)

    @Before
    fun init() {
        ActivityScenario.launch(FactsActivity::class.java)
    }

    @Test
    fun checkTextSizeOnRecyclerViewItem() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

        val recyclerView = rule.activity.findViewById<RecyclerView>(R.id.factsRecyclerView)

        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.searchEditText)).perform(typeText("test"), pressImeActionButton())
        onView(withId(R.id.factsRecyclerView)).check(matches(isDisplayed()))

        val itemsCount = recyclerView.adapter?.itemCount ?: 0

        for (position in 0 until itemsCount) {
            onView(withId(R.id.factsRecyclerView)).apply {
                perform(scrollToPosition<RecyclerView.ViewHolder>(position))
                check(matches(checkTextSizeAt(position, R.id.fact)))
            }
        }
    }

    @Test
    fun checkUncategorizedLabelItem() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)

        onView(withId(R.id.search)).perform(click())
        onView(withId(R.id.searchEditText)).perform(typeText("test"), pressImeActionButton())
        onView(withId(R.id.factsRecyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.factsRecyclerView)).apply {
            perform(scrollToPosition<RecyclerView.ViewHolder>(5))
            check(matches(checkUncategorizedLabel(5, R.id.category)))
        }
    }
}