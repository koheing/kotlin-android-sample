package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.models.Todo
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoListViewAdapter(): RecyclerView.Adapter<TodoViewHolder>() {

    companion object {
        private const val TAG = "TodoListViewAdapter"
    }

    var todoList: List<Todo> = emptyList()

    private lateinit var onClickAction: (message: String) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_list_item, parent, false)

        view.setOnClickListener { onClickAction(it.textView.text.toString()) }
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int, payload: MutableList<Any>) {
        if (payload.isEmpty()) { onBindViewHolder(holder, position); return; }

        val bundle = payload[0] as Bundle

        for (key in bundle.keySet()) {
            when (key) {
                "todo" -> holder.todoView.text = bundle.getString("todo")
            }
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todoList[position]

        holder.apply {
            this.todoView.text = todo.todo
        }
    }

    override fun getItemCount(): Int = todoList.count()

    fun setOnClickListener(onClickAction: (message: String) -> Unit) {
        this.onClickAction = onClickAction
    }
}