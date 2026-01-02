package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchTodos()
    }

    private fun fetchTodos() {
        lifecycleScope.launch(Dispatchers.IO) {
            val todos = mutableListOf<Todo>()
            val url = URL("https://jsonplaceholder.typicode.com/todos")
            val connection = url.openConnection() as HttpURLConnection

            try {
                connection.requestMethod = "GET"
                connection.connect()

                val response = connection.inputStream.bufferedReader().readText()
                val jsonArray = JSONArray(response)

                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    todos.add(
                        Todo(
                            userId = obj.getInt("userId"),
                            id = obj.getInt("id"),
                            title = obj.getString("title"),
                            completed = obj.getBoolean("completed")
                        )
                    )
                }

                withContext(Dispatchers.Main) {
                    recyclerView.adapter = TodoAdapter(todos)
                }

            } finally {
                connection.disconnect()
            }
        }
    }
}
