package com.example.appinterfacesembarquees

import android.media.MediaPlayer

class Notes(val x:Int, val y:Int, val pX:Int, val pY:Int){
    val notes = arrayOf("Fa", "FaD", "Sol", "SolD", "La", "LaD", "Si", "Do", "DoD", "Re", "ReD", "Mi", "Fa2", "FaD2", "Sol2", "SolD2", "La2", "LaD2", "Si2", "Do2", "DoD2", "Re2", "ReD2", "Mi2")
    var note = ""

    init {

        var num = x*14/pX

        note = when(num){
            0 -> if (((y*5/pY) <= 2) && ((x*7/pX) >= 4)) notes[1] else notes[0]
            1 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[1] else if((x*7/pX) >= 4) notes[3] else notes[2]) else notes[2]
            2 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[3] else if((x*7/pX) >= 4) notes[5] else notes[4]) else notes[4]
            3 -> if (((y*5/pY) <= 2) && ((x*7/pX) <= 1)) notes[5] else notes[6]

            4 -> if (((y*5/pY) <= 2) && ((x*7/pX) >= 4)) notes[8] else notes[7]
            5 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[8] else if((x*7/pX) >= 4) notes[10] else notes[9]) else notes[9]
            6 -> if (((y*5/pY) <= 2) && ((x*7/pX) <= 1)) notes[10] else notes[11]

            7 -> if (((y*5/pY) <= 2) && ((x*7/pX) >= 4)) notes[13] else notes[12]
            8 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[13] else if((x*7/pX) >= 4) notes[15] else notes[14]) else notes[14]
            9 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[15] else if((x*7/pX) >= 4) notes[17] else notes[16]) else notes[16]
            10 -> if (((y*5/pY) <= 2) && ((x*7/pX) <= 1)) notes[17] else notes[18]

            11 -> if (((y*5/pY) <= 2) && ((x*7/pX) >= 4)) notes[20] else notes[19]
            12 -> if ((y*5/pY) <= 2) (if((x*7/pX) <= 1) notes[20] else if((x*7/pX) >= 4) notes[22] else notes[21]) else notes[21]
            13 -> if (((y*5/pY) <= 2) && ((x*7/pX) <= 1)) notes[22] else notes[23]

            else -> ""

        }
    }

    override fun toString(): String {
        return note
    }

}

