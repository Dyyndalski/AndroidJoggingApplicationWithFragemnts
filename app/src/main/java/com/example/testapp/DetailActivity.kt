package com.example.testapp;


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val frag =
            supportFragmentManager.findFragmentById(R.id.detail_frag) as JoggingDetailFragment?

        frag!!.setCocktail((intent.extras!![EXTRA_COCKTAIL_ID] as Int).toLong())

    }

    companion object {
        const val EXTRA_COCKTAIL_ID = "id"
    }
}