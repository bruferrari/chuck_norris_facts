package com.bferrari.stonechallenge.extensions

import android.content.Context
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

const val BIG_FONT_SIZE = 40f
const val SMALL_FONT_SIZE = 16f
const val MAXIMUM_CHARS = 80

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.snack(@StringRes messageRes: Int, length: Int = Snackbar.LENGTH_LONG) {
    snack(resources.getString(messageRes), length)
}

fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    Snackbar.make(this, message, length).show()
}

fun TextView.applyTextSizeRule() {
    textSize = if (text.length > MAXIMUM_CHARS) SMALL_FONT_SIZE.spToPx(context) else BIG_FONT_SIZE.spToPx(context)
}

fun Float.spToPx(context: Context) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, this, context.resources.displayMetrics)

fun Float.pxToSp(context: Context) =
    this / context.resources.displayMetrics.scaledDensity
