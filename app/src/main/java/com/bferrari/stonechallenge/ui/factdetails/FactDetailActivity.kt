package com.bferrari.stonechallenge.ui.factdetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bferrari.domain.Fact
import com.bferrari.stonechallenge.R
import com.bferrari.stonechallenge.utils.Consts.FACT_INTENT
import kotlinx.android.synthetic.main.activity_fact_detail.*

class FactDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact_detail)

        val fact: Fact = intent.getSerializableExtra(FACT_INTENT) as Fact
        factText.text = fact.value
        checkFactButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(fact.url) }
            startActivity(intent)
        }
    }
}