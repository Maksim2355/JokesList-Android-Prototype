package com.example.kolodeznapp.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kolodeznapp.API.JokeApi
import com.example.kolodeznapp.AdapterJokes
import com.example.kolodeznapp.dialog.AddJokeDialog
import com.example.kolodeznapp.R
import com.example.kolodeznapp.dbapi.JokeDatabaseHelper
import com.example.kolodeznapp.model.Joke
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListJokesFragment : Fragment(), UpdateAdapter {
    private lateinit var dbApi: JokeApi
    private lateinit var dbDataList: Array<Joke>
    private lateinit var adapter: AdapterJokes
    private lateinit var listRecycler: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val v: View = inflater.inflate(R.layout.fragment_list_jokes, container, false)
        listRecycler = v.findViewById<RecyclerView>(R.id.recycler_jokes_list)
        val layoutManager = LinearLayoutManager(context)
        listRecycler.layoutManager = layoutManager
        dbApi = JokeDatabaseHelper(requireActivity())
        dbDataList = dbApi.getAllJoke()
        updateListJoke()
        // Inflate the layout for this fragment
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn: FloatingActionButton = view.findViewById(R.id.fab)
        btn.setOnClickListener {
            val addJokeDialog = AddJokeDialog(this)
            parentFragmentManager.let { it1 -> addJokeDialog.show(it1, "custom") }
        }
    }

    override fun updateListJoke() {
        dbDataList = dbApi.getAllJoke()
        adapter = AdapterJokes(dbDataList, dbApi, this)
        listRecycler.adapter = adapter
    }
}
