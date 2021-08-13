package com.testjoke.data.repository

import com.testjoke.data.remote.JokesRemoteDataSource
import javax.inject.Inject

class JokesRepository @Inject constructor(private val jokesRemoteDataSource: JokesRemoteDataSource) {

    suspend fun getJokes(count: Int) = jokesRemoteDataSource.getJokes(count)
}
