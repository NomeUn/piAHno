package com.example.appinterfacesembarquees

import android.media.MediaPlayer

class Notes(val note: Int){
    val notes = arrayOf("Fa", "Sol", "La", "Si", "Do", "Re", "Mi", "Fa2", "Sol2", "La2", "Si2", "Do2", "Re2", "Mi2")

    override fun toString(): String {
        return notes[note]
    }



}

