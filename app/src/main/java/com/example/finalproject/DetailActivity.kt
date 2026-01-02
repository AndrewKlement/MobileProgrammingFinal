package com.example.finalproject

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        findViewById<TextView>(R.id.userIdText).text =
            "User ID: ${intent.getIntExtra("userId", 0)}"

        findViewById<TextView>(R.id.idText).text =
            "ID: ${intent.getIntExtra("id", 0)}"

        findViewById<TextView>(R.id.titleText).text =
            "Title: ${intent.getStringExtra("title")}"

        findViewById<TextView>(R.id.completedText).text =
            "Completed: ${intent.getBooleanExtra("completed", false)}"
    }
}