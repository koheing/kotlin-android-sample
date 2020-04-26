package com.example.sampleapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.models.Todo
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_MESSAGE = "com.example.sampleapp"

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModelFactory = MainActivityViewModelFactory()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var todoListRecyclerView: RecyclerView
    private lateinit var todoListAdapter: RecyclerView.Adapter<*>
    private lateinit var todoListManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this, mainActivityViewModelFactory)
            .get(MainActivityViewModel::class.java)

        mainActivityViewModel.todoListLiveData.observe(this, Observer { bindTodo(it) })

        initializeTodoListView()
    }

    fun sendMessage(view: View) {
        val message = messageView.text.toString()
        mainActivityViewModel.addTodo(message)
    }

    private fun bindTodo(todoList: List<Todo>) {
        if (todoList.count() == 0) {
            return
        }

        val adapter = todoListView.adapter as TodoListViewAdapter
        val diff = DiffUtil.calculateDiff(CheckDiffInTodoList(adapter.todoList, todoList), true)

        adapter.todoList = todoList

        diff.dispatchUpdatesTo(adapter)
    }

    private fun initializeTodoListView() {
        todoListRecyclerView = todoListView
        todoListManager = LinearLayoutManager(this)
        todoListAdapter = TodoListViewAdapter()
        todoListRecyclerView.apply {
            this.layoutManager = todoListManager
            this.adapter = todoListAdapter
        }
    }
}
