package com.example.sampleapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp.models.Todo

class TodoListViewAdapter(): RecyclerView.Adapter<TodoViewHolder>() {

    companion object {
        private const val TAG = "TodoListViewAdapter"
    }

    var todoList: List<Todo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_list_item, parent, false)

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
}