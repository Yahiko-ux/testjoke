package com.testjoke.ui.jokes

import androidx.lifecycle.*
import com.testjoke.data.repository.JokesRepository
import com.testjoke.data.entities.ApiResponse
import com.testjoke.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokesViewModel @Inject constructor(private val repository: JokesRepository) : ViewModel() {

    private val _jokes = MutableLiveData<Resource<ApiResponse>>()
    private val _countJokes: MutableStateFlow<Int> = MutableStateFlow(0)
    val jokes: LiveData<Resource<ApiResponse>> = _jokes
    val countJokes: StateFlow<Int> = _countJokes

    init {
        getJokes()
    }

    fun setCount(count: Int) {
        _countJokes.value = count
    }

    private fun getJokes() {
        viewModelScope.launch {
            countJokes
                .drop(1)
                .collect {
                    _jokes.value = repository.getJokes(it)
                }
        }
    }
}
