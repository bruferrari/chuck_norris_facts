package com.bferrari.stonechallenge.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle

inline fun <reified T : Activity> Activity.navigateForResult(
        codeRequest: Int,
        bundle: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    intent.apply {
        bundle?.let { putExtras(bundle) }
        startActivityForResult(this, codeRequest)
    }
}
