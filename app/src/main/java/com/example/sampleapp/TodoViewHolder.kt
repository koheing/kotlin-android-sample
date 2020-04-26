package com.example.sampleapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val todoView = view.todoView
}
