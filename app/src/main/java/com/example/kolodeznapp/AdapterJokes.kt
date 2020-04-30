package com.example.kolodeznapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kolodeznapp.API.JokeApi
import com.example.kolodeznapp.model.Joke

class AdapterJokes(private val jokes: Array<Joke>, private val dbApi: JokeApi):
    RecyclerView.Adapter<AdapterJokes.ViewHolder>() {
    private lateinit var mTitleItem: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.item_joke, parent, false)
        mTitleItem = v.findViewById(R.id.title_joke_item)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v), View.OnClickListener
    {
        init {

        }

        fun bind(position: Int){
            mTitleItem.text = jokes[position].title
        }

        override fun onClick(v: View?) {
            TODO("Not yet implemented")
        }


    }
}