package com.example.kolodeznapp.dbapi

object DbSchema {
    object JokesTable {
        const val NAME = "Jokes"
        object Cols {
            const val ID = "_id"
            const val TITLE = "title"
            const val CONTENT = "content"
        }
    }
}

