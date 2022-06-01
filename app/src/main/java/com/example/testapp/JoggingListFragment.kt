package com.example.testapp;

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.ListFragment

class JoggingListFragment : ListFragment() {
    internal interface Listener {
        fun itemClicked(id: Long)
    }

    private var listener: Listener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedinstanceState: Bundle?): View? {
        val allContacts: List<Sport> =
            (DatabaseHandler.getInstance(context)?.getAllSports() ?: null) as List<Sport>;
        val names = arrayOfNulls<String>(allContacts.size)
        for (i in allContacts.indices) {
            names[i] = allContacts[i].name
        }
        val adapter = ArrayAdapter(inflater.context, R.layout.simple_list_item_1, names)
        listAdapter = adapter
        return super.onCreateView(inflater, container, savedinstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as Listener
    }

    override fun onListItemClick(listView: ListView, itemView: View, position: Int, id: Long) {
        listener?.itemClicked(id)
    }
}