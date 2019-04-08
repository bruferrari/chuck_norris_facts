package com.bferrari.stonechallenge

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import com.bferrari.stonechallenge.components.Tag
import com.bferrari.stonechallenge.extensions.BIG_FONT_SIZE
import com.bferrari.stonechallenge.extensions.SMALL_FONT_SIZE
import com.bferrari.stonechallenge.extensions.pxToSp
import com.bferrari.stonechallenge.ui.searchfacts.PastSearchesAdapter
import org.hamcrest.Description
import org.hamcrest.Matcher

class Matchers {

    companion object {
        const val UNCATEGORIZED = "Uncategorized"

        fun checkTextSizeAt(position: Int, targetViewId: Int): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

                override fun describeTo(description: Description?) {
                    description?.apply {
                        appendText("TextView at $position")
                    }
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    var isTextSizeCorrect = false

                    val adapter = recyclerView.adapter

                    adapter?.let {
                        recyclerView.findViewHolderForAdapterPosition(position)?.run {
                            val targetView = itemView.findViewById<TextView>(targetViewId)
                            isTextSizeCorrect = checkTextSizeValue(targetView)
                        }
                        return isTextSizeCorrect
                    }
                    return isTextSizeCorrect
                }

            }
        }

        private fun checkTextSizeValue(textView: TextView): Boolean {
            return when (textView.text.length) {
                in 1..80 -> textView.textSize.pxToSp(textView.context) == BIG_FONT_SIZE
                else -> textView.textSize.pxToSp(textView.context) == SMALL_FONT_SIZE
            }
        }

        fun checkForDuplicatedEntries(): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("No duplicated entries")
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    val adapter = recyclerView.adapter as PastSearchesAdapter
                    return adapter.data == adapter.data.distinct()
                }

            }
        }

        fun checkUncategorizedLabel(position: Int, targetViewId: Int): Matcher<View> {
            return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("has uncategorized label at $position")
                }

                override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                    val adapter = recyclerView.adapter

                    adapter?.let {
                        recyclerView.findViewHolderForAdapterPosition(position)?.run {
                            val targetView = itemView.findViewById<Tag>(R.id.category)
                            val tag = targetView.getTagAt(0)

                            if (tag == UNCATEGORIZED)
                                return true

                            return false
                        }
                    }
                    return false
                }

            }
        }

    }
}