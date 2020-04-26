package com.example.sampleapp

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.example.sampleapp.models.Todo

class CheckDiffInTodoList(
    private val newList: List<Todo>, private val oldList: List<Todo>): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].todo == newList[newItemPosition].todo
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getNewListSize(): Int = newList.size

    override fun getOldListSize(): Int = oldList.size

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val old = oldList[oldItemPosition]
        val new = newList[newItemPosition]

        val diff = Bundle()

        val isSameTodo = old.todo == new.todo

        if(!isSameTodo) diff.putString("todo", new.todo)

        if(diff.size() == 0) return null

        return diff
    }
}