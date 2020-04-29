package com.example.kolodeznapp.model


data class Joke(var id: Long, val title: String, var jokeContent: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Joke
        if (title != other.title) return false
        if (jokeContent != other.jokeContent) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + jokeContent.hashCode()
        return result
    }
}