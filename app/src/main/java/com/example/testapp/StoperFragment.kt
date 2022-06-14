package com.example.testapp

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

class StoperFragment : Fragment(), View.OnClickListener {
    private var seconds = 0
    private var running = false
    private var wasRunning = false
    private var sportId = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        SavedInstanceState: Bundle?
    ): View? {
        val layout = inflater.inflate(R.layout.fragment_stoper, container, false)
        runStoper(layout)
        val startButton = layout.findViewById<View>(R.id.start_button) as Button
        startButton.setOnClickListener(this)
        val stopButton = layout.findViewById<View>(R.id.stop_button) as Button
        stopButton.setOnClickListener(this)
        val resetButton = layout.findViewById<View>(R.id.reset_button) as Button
        resetButton.setOnClickListener(this)
        val saveButton = layout.findViewById<View>(R.id.save_button) as Button
        saveButton.setOnClickListener(this)
        return layout
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
        running = false
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            running = true
        }
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt("seconds", seconds)
        savedInstanceState.putBoolean("running", running)
        savedInstanceState.putBoolean("wasRunning", wasRunning)
    }

    private fun onClickStart() {
        running = true
    }

    private fun onClickStop() {
        running = false
    }

    private fun onClickReset() {
        running = false
        seconds = 0
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun onClickSave() {
        running = false
        DatabaseHandler.getInstance(context)?.updateDateAndTime(seconds, global.sportId)
        seconds = 0
    }

    private fun runStoper(view: View) {
        val timeView = view.findViewById<View>(R.id.time_view) as TextView
        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) {
                    seconds++
                }
                handler.postDelayed(this, 1000)
            }
        })
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun onClick(v: View) {
        when (v.id) {
            R.id.start_button -> onClickStart()
            R.id.stop_button -> onClickStop()
            R.id.reset_button -> onClickReset()
            R.id.save_button -> onClickSave()
        }
    }

    class global {
        companion object {
            var sportId: Int = 0;
            fun setId(id: Long) {
                sportId = id.toInt()
            }

        }
    }
}