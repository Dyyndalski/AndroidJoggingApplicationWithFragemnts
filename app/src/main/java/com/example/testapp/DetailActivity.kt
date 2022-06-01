package com.example.testapp;


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        (supportFragmentManager.findFragmentById(R.id.detail_frag) as JoggingDetailFragment?)?.setCocktail(
            intent.extras!![EXTRA_COCKTAIL_ID] as Int
        )
    }

    companion object {
        const val EXTRA_COCKTAIL_ID = "id"
    }
}