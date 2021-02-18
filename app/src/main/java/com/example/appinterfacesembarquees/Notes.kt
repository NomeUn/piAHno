package com.example.appinterfacesembarquees

import android.media.MediaPlayer

class Notes(val x:Int, val y:Int, val pX:Int, val pY:Int){
    val notes = arrayOf("Fa", "FaD", "Sol", "SolD", "La", "LaD", "Si", "Do", "DoD", "Re", "ReD", "Mi", "Fa2", "FaD2", "Sol2", "SolD2", "La2", "LaD2", "Si2", "Do2", "DoD2", "Re2", "ReD2", "Mi2")
    val note = ""

    init {

        var num = x*14/pX

        note = when(num){
            0 -> if (((y*5/pY) >= 2)&&((x*70/pX) >= 4)) notes[1] else notes[0]
            1 -> if ((y*5/pY) >= 2) (if((x*70/pX) <= 1) notes[1] else if((x*70/pX) >= 4) notes[3] else notes[2]) else notes[2]
        }
    }

    override fun toString(): String {
        return note
    }






}

