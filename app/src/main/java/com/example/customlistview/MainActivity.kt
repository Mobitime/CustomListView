package com.example.customlistview

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCreateStore = findViewById<Button>(R.id.btnCreateStore)
        btnCreateStore.setOnClickListener{
            val intent = Intent(this, StoreActivity::class.java)
            startActivity(intent)
        }
    }
}