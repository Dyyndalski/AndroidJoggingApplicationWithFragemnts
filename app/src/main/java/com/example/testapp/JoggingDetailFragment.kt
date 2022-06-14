package com.example.testapp;

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class JoggingDetailFragment : Fragment() {
    private var sportId: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            sportId = savedInstanceState.getLong("sportId");
        } else {
            val bundle = Bundle()
            bundle.putInt("index", sportId.toInt())
            val stoper = StoperFragment()
            val ft = childFragmentManager.beginTransaction()
            ft.replace(R.id.stoper_container, stoper)
            stoper.arguments = bundle
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_jogging_detail, container, false)
    }

    fun setCocktail(id: Long) {
        sportId = id
    }

    override fun onStart() {
        super.onStart()
        val view = view
        val sport = DatabaseHandler.getInstance(context)?.getSportById(sportId)

        if (sport != null) {
            Log.e("xDDDD", sport.recordTime.toString())
        };

        if (view != null) {
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            if (sport != null) {
                title.setText(sport.name)
            }
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            if (sport != null) {
                description.setText(sport.details)
            }

            val recordDate = view.findViewById<View>(R.id.record_date) as TextView
            if (sport != null) {
                recordDate.text =
                    if (sport.recordDate.toString() == "0000-00-00") "brak" else sport.recordDate
            }

            val recordTime = view.findViewById<View>(R.id.record_time) as TextView
            if (sport != null) {
                recordTime.text =
                    if (sport.recordTime.toString() == "00:00:00") " " else sport.recordTime
            }

            val lastDate = view.findViewById<View>(R.id.last_date) as TextView
            if (sport != null) {
                lastDate.text =
                    if (sport.lastDate.toString() == "0000-00-00") "brak" else sport.lastDate
            }

            val lastTime = view.findViewById<View>(R.id.last_time) as TextView
            if (sport != null) {
                lastTime.text =
                    if (sport.lastTime.toString() == "00:00:00") " " else sport.lastTime
            }
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putLong("sportId", sportId)
    }
}