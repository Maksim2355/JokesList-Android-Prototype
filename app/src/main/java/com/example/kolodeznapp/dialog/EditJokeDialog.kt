package com.example.kolodeznapp.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.kolodeznapp.API.JokeApi
import com.example.kolodeznapp.R
import com.example.kolodeznapp.UI.UpdateAdapter
import com.example.kolodeznapp.dbapi.JokeDatabaseHelper
import com.example.kolodeznapp.model.Joke

class EditJokeDialog(private var joke:Joke): DialogFragment(), View.OnClickListener{
    private lateinit var mBtnOk: Button
    private lateinit var mBtnNone: Button
    private lateinit var mEditTitleJoke: EditText
    private lateinit var mEditJoke: EditText
    private lateinit var dbAPI: JokeApi
    private lateinit var updateList: UpdateAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dbAPI = JokeDatabaseHelper(requireActivity())
        val v: View = inflater.inflate(R.layout.dialog_add_joke, null)
        mBtnOk = v.findViewById(R.id.btn_ok)
        mBtnNone = v.findViewById(R.id.btn_cancel)
        mEditTitleJoke = v.findViewById(R.id.title_joke)
        mEditJoke = v.findViewById(R.id.joke_add)

        mEditTitleJoke.setText(joke.title)
        mEditJoke.setText(joke.jokeContent)
        mBtnOk.setOnClickListener(this)
        mBtnNone.setOnClickListener(this)
        return v
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btn_ok -> {
                val titleJoke = mEditTitleJoke.text
                val contentJoke = mEditJoke.text
                if(titleJoke.isNotEmpty() && contentJoke.isNotEmpty()) {
                    joke.title = titleJoke.toString()
                    joke.jokeContent = contentJoke.toString()
                    dbAPI.editJoke(joke)
                    updateList.updateListJoke()
                }
                dismiss()
            }
            R.id.btn_cancel -> {
                dismiss()
            }
        }
    }
}