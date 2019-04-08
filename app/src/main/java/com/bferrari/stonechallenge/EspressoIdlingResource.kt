package com.bferrari.stonechallenge

import androidx.test.espresso.idling.CountingIdlingResource

class EspressoIdlingResource {

    companion object {
        const val IDLING_RESOURCE_NAME = "countingIdlingResource"

        val idlingResource = CountingIdlingResource(IDLING_RESOURCE_NAME)
    }
}