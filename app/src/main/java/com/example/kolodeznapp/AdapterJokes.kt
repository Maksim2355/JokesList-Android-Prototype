package com.example.kolodeznapp


import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.kolodeznapp.API.JokeApi
import com.example.kolodeznapp.UI.UpdateAdapter
import com.example.kolodeznapp.dialog.AddJokeDialog
import com.example.kolodeznapp.dialog.EditJokeDialog
import com.example.kolodeznapp.model.Joke
import java.sql.Time
import java.util.concurrent.TimeUnit


class AdapterJokes(private val jokes: Array<Joke>, private val dbApi: JokeApi, private val fragment: Fragment):
    RecyclerView.Adapter<AdapterJokes.ViewHolder>() {
    private lateinit var mTitleItem: TextView
    private lateinit var mContentItem: TextView

    private lateinit var mBtnDelete: ImageButton
    private lateinit var mBtnEdit: ImageButton
    private lateinit var mBtnItemExtension: ImageButton

    private lateinit var updateAdapter: UpdateAdapter
    private lateinit var animRotate: Animation
    private lateinit var animTranslate: Animation


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.item_joke, parent, false)
        mTitleItem = v.findViewById(R.id.title_joke_item)
        mContentItem = v.findViewById(R.id.content_joke)

        mBtnDelete = v.findViewById(R.id.btnDelItem)
        mBtnEdit = v.findViewById(R.id.btnEditItem)
        mBtnItemExtension = v.findViewById(R.id.item_extension)
        updateAdapter = fragment as UpdateAdapter
        
        animRotate = AnimationUtils.loadAnimation(context, R.anim.rotate)
        animTranslate = AnimationUtils.loadAnimation(context, R.anim.translate)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return jokes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            mBtnEdit.setOnClickListener(this)
            mBtnDelete.setOnClickListener(this)
            mBtnItemExtension.setOnClickListener(this)
        }

        fun bind(position: Int) {
            mTitleItem.text = jokes[position].title
        }

        override fun onClick(v: View?) {
            val mContextItem = view.findViewById<TextView>(R.id.content_joke)
            when (v!!.id) {
                R.id.item_extension -> {
                    if (mContextItem.visibility == GONE){
                        v.startAnimation(animRotate)
                        v.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_24px)

                        mContextItem.visibility = VISIBLE
                        mContextItem.text = jokes[adapterPosition].jokeContent
                    }else{
                        v.startAnimation(animRotate)
                        v.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_24px)
                        mContextItem.visibility = GONE
                    }
                }
                R.id.btnEditItem -> {
                    val editJokeDialog = EditJokeDialog(jokes[adapterPosition])
                    fragment.parentFragmentManager.let { it1 -> editJokeDialog.show(it1, "custom 2") }
                }
                R.id.btnDelItem->{
                    view.startAnimation(animTranslate)
                    dbApi.deleteJoke(jokes[adapterPosition].id)
                    updateAdapter.updateListJoke()

                }
            }
        }
    }
}