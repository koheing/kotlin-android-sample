package com.example.sampleapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.models.Todo
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager

const val EXTRA_MESSAGE = "com.example.sampleapp"

class MainActivity : AppCompatActivity() {

    private val mainActivityViewModelFactory = MainActivityViewModelFactory()

    private lateinit var mainActivityViewModel: MainActivityViewModel

    private lateinit var todoListAdapter: RecyclerView.Adapter<*>
    private lateinit var todoListManager: RecyclerView.LayoutManager

    private lateinit var inputMethodManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this, mainActivityViewModelFactory)
            .get(MainActivityViewModel::class.java)

        mainActivityViewModel.todoListLiveData.observe(this, Observer { bindTodo(it) })

        inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        initializeTodoListView()
    }

    fun sendMessage(v: View) {
        val message = messageView.text.toString()
        mainActivityViewModel.addTodo(message)

        messageView.clearFocus()

        inputMethodManager.hideSoftInputFromWindow(messageView.windowToken, 0)
    }

    private fun bindTodo(todoList: List<Todo>) {
        if (todoList.count() == 0) {
            return
        }

        val adapter = todoListView.adapter as TodoListViewAdapter
        adapter.setOnClickListener{ moveTo(it) }
        val diff = DiffUtil.calculateDiff(CheckDiffInTodoList(adapter.todoList, todoList), true)

        adapter.todoList = todoList

        diff.dispatchUpdatesTo(adapter)
    }

    private fun moveTo(message: String) {
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }

        startActivity(intent)
    }

    private fun initializeTodoListView() {
        todoListManager = LinearLayoutManager(this)
        todoListAdapter = TodoListViewAdapter()
        todoListView.apply {
            this.layoutManager = todoListManager
            this.adapter = todoListAdapter
        }
    }
}
