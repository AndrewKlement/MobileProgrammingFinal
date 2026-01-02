package com.example.finalproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: List<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleText)
        val status: TextView = view.findViewById(R.id.statusText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todo = todos[position]

        holder.title.text = "Title: ${todo.title}"
        holder.status.text =
            if (todo.completed) "Status: Completed" else "Status: Not Completed"

        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("userId", todo.userId)
            intent.putExtra("id", todo.id)
            intent.putExtra("title", todo.title)
            intent.putExtra("completed", todo.completed)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = todos.size
}
