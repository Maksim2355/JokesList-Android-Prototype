package com.example.kolodeznapp.API

import com.example.kolodeznapp.model.Joke

/**
 * Удаление анекдота
 * Добавление анекдота
 * Изменение анекдота
 * Получение всех анекдотов
 * Получение анекдота по ID
 */
interface JokeApi {

    fun deleteJoke(id: Int)

    fun editJoke(joke: Joke)

    fun addJoke(joke: Joke)

    fun getJokeById(id: Long): Joke

    fun getAllJoke(): Array<Joke>
}