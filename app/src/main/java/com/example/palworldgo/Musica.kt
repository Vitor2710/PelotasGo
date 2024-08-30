package com.example.palworldgo

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder

class MusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.ost).apply {
            setVolume(1.0f, 1.0f)
            isLooping = true
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (mediaPlayer?.isPlaying == false) {
            mediaPlayer?.start()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
        }
        mediaPlayer = null
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
}