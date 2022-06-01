package com.example.testapp;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.testapp.R

class JoggingDetailFragment : Fragment() {
    private var sportId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            sportId = savedInstanceState.getLong("sportId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jogging_detail, container, false)
    }

    fun setCocktail(id: Int) {
        sportId = id.toLong()
    }

    override fun onStart() {
        super.onStart()
        val view = view
        val sports: List<Sport>? = DatabaseHandler.getInstance(context)?.getAllSports();
        val sport = sports?.get(sportId.toInt());
        if (view != null) {
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            if (sport != null) {
                title.setText(sport.name)
            }
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            description.setText(sport?.details ?: null)
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("sportId", sportId)
    }
}