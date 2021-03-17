package com.example.appinterfacesembarquees

import android.content.Context
import android.media.MediaPlayer
import java.util.*

class MetronomeTimerTask : TimerTask() {
    var context : Context? = null

    override fun run() {
        var mediaPlayer = MediaPlayer.create(context, R.raw.woodblock)
        mediaPlayer.start()
    }

    fun context(ct: Context){
        context = ct
    }

}
