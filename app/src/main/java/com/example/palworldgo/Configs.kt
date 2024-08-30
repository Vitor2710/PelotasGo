package com.example.palworldgo

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class Configs : AppCompatActivity() {
    private lateinit var vbutton: ImageButton
    private lateinit var devbutton: ImageButton
    private lateinit var musbutton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.config)

        musbutton = findViewById(R.id.musbutton)
        devbutton = findViewById(R.id.devbutton)
        vbutton = findViewById(R.id.vbutton)

        updateMusicService()
        updateDevModeButton()

        musbutton.setOnClickListener {
            SharedVariables.taon2 = !SharedVariables.taon2
            updateMusicService()
        }

        devbutton.setOnClickListener {
            SharedVariables.taon = !SharedVariables.taon
            updateDevModeButton()
        }

        vbutton.setOnClickListener {
            val intent = Intent(this, Menu::class.java)
            startActivity(intent)
        }
    }

    private fun updateMusicService() {
        if (SharedVariables.taon2) {
            val intent = Intent(this, MusicService::class.java)
            startService(intent)
            musbutton.setImageResource(R.drawable.music_button)
        } else {
            val intent = Intent(this, MusicService::class.java)
            stopService(intent)
            musbutton.setImageResource(R.drawable.music_button_off)
        }
    }

    private fun updateDevModeButton() {
        devbutton.setImageResource(
            if (SharedVariables.taon) R.drawable.dev_mode_button else R.drawable.dev_mode_button_off
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }
}
