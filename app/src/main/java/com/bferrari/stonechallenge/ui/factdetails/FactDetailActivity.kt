package com.bferrari.stonechallenge.ui.factdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bferrari.domain.Fact
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.utils.Consts.FACT_INTENT
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_fact_detail.*

class FactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact_detail)

        setupUi()
//        WorkerDispatcher.dispatch(this)
    }

    private fun setupUi() {
        val fact: Fact = intent.getSerializableExtra(FACT_INTENT) as Fact

        factText.text = fact.value

        checkFactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(fact.url) }
            startActivity(intent)
        }

        loadImage(fact.iconUrl, iconImage)

        factDetailContainer.transitionToEnd()
    }

    private fun loadImage(url: String?, imageView: ImageView) {
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_extension_black_24dp)
            .resize(100, 100)
            .into(imageView)
    }
}