package com.example.kolodeznapp.dbapi

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kolodeznapp.API.JokeApi
import com.example.kolodeznapp.model.Joke


class JokeDatabaseHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION), JokeApi {
    private val dbWrite = writableDatabase
    private val dbRead = readableDatabase

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE =
            "create table $TABLE_NAME ($COLS_ID integer primary key ,$COLS_TITLE text, " +
                    "$COLS_CONTENT)"
        db.execSQL(CREATE_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val textUpdate = "drop table if exists $TABLE_NAME"
        db.execSQL(textUpdate)
        onCreate(db)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "jokes.db"
        const val TABLE_NAME = DbSchema.JokesTable.NAME
        const val COLS_ID = DbSchema.JokesTable.Cols.ID
        const val COLS_TITLE = DbSchema.JokesTable.Cols.TITLE
        const val COLS_CONTENT = DbSchema.JokesTable.Cols.CONTENT
    }

    override fun deleteJoke(id: Int) {
        TODO()
    }

    override fun editJoke(joke: Joke) {
        TODO("Not yet implemented")
    }

    override fun addJoke(joke: Joke) {
        val values = ContentValues()
        values.put(COLS_TITLE, joke.title)
        values.put(COLS_CONTENT, joke.jokeContent)
        joke.id = dbWrite.insert(TABLE_NAME, null, values)
    }

    override fun getJokeById(id: Int): Joke {
        TODO("Not yet implemented")
    }

    override fun getAllJoke(): Array<Joke> {
        val c: Cursor = dbRead.query(TABLE_NAME, null,
            null, null, null, null, null)
        var jokeArray = emptyArray<Joke>()
        if (c.moveToFirst()) {
            // определяем номера столбцов по имени в выборке
            val idColsIndex = c.getColumnIndex(COLS_ID)
            val titleColsIndex = c.getColumnIndex(COLS_TITLE)
            val contentColIndex = c.getColumnIndex(COLS_CONTENT)
            do {
                jokeArray +=
                    Joke(c.getLong(idColsIndex),
                         c.getString(titleColsIndex),
                         c.getString(contentColIndex))
            } while (c.moveToNext())
        }
        c.close()
        return jokeArray
    }


}