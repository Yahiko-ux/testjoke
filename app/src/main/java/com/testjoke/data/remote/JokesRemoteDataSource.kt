package com.testjoke.data.remote

import javax.inject.Inject

class JokesRemoteDataSource @Inject constructor(private val apiService: ApiService) :
    BaseDataSource() {

    suspend fun getJokes(count: Int) = getResult { apiService.getJokes(count) }
}