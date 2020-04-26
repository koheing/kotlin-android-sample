package com.example.sampleapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sampleapp.extensions.add
import com.example.sampleapp.models.Todo

class MainActivityViewModel: ViewModel() {

    companion object {
        private const val TAG = "MainActivityViewModel"
    }

    private val _todoListLiveData: MutableLiveData<List<Todo>> = MutableLiveData(arrayListOf())

    val todoListLiveData: LiveData<List<Todo>> = _todoListLiveData

    fun addTodo(message: String) {
        val todo = Todo(message)
        _todoListLiveData.add(todo)
    }
}
