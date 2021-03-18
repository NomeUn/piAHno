package com.example.appinterfacesembarquees

import android.content.Context
import android.media.MediaPlayer
import android.view.View
import java.util.*

class MetronomeTimerTask : TimerTask() {
    var context : Context? = null

    override fun run() {
        /** MédiaPlayer sur le son du métronome */
        var mediaPlayer = MediaPlayer.create(context, R.raw.woodblock)
        mediaPlayer.start()
        /** On release le mediaPlayer lorsque le son est fini */
        mediaPlayer.setOnCompletionListener { mediaPlayer ->
            try {
                mediaPlayer.release()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        
    }

    /** Fonction pour reprendre le context de l'activité principale */
    fun context(ct: Context){
        context = ct
    }

}
