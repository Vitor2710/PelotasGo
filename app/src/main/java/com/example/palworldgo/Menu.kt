package com.example.palworldgo

import android.content.Intent
import android.graphics.Bitmap.Config
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity

class Menu : AppCompatActivity() {
    private lateinit var jbutton: ImageButton
    private lateinit var cbutton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        jbutton = findViewById(R.id.jbutton)
        cbutton = findViewById(R.id.cbutton)
        jbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        cbutton.setOnClickListener {
                val intent2 = Intent(this, Configs::class.java)
                startActivity(intent2)
            }
        }
    }