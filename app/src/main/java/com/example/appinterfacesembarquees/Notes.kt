package com.example.appinterfacesembarquees

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import java.io.File


class Notes(val x: Int, val y: Int, val pX: Int, val pY: Int, applicationContext: Context){
    val notes = arrayOf("fa", "fad", "sol", "sold", "la", "lad", "si", "do", "dod", "re", "red", "mi", "fa2", "fad2", "sol2", "sold2", "la2", "lad2", "si2", "do2", "dod2", "re2", "red2", "mi2")
    var note = ""
    var context = applicationContext

    init {

        var num = x*14/pX
        note = when(num){
            0 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) >= 4)) notes[1] else notes[0]
            1 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[1] else if ((x * 7 / pX) >= 4) notes[3] else notes[2]) else notes[2]
            2 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[3] else if ((x * 7 / pX) >= 4) notes[5] else notes[4]) else notes[4]
            3 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) <= 1)) notes[5] else notes[6]

            4 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) >= 4)) notes[8] else notes[7]
            5 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[8] else if ((x * 7 / pX) >= 4) notes[10] else notes[9]) else notes[9]
            6 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) <= 1)) notes[10] else notes[11]

            7 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) >= 4)) notes[13] else notes[12]
            8 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[13] else if ((x * 7 / pX) >= 4) notes[15] else notes[14]) else notes[14]
            9 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[15] else if ((x * 7 / pX) >= 4) notes[17] else notes[16]) else notes[16]
            10 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) <= 1)) notes[17] else notes[18]

            11 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) >= 4)) notes[20] else notes[19]
            12 -> if ((y * 5 / pY) <= 2) (if ((x * 7 / pX) <= 1) notes[20] else if ((x * 7 / pX) >= 4) notes[22] else notes[21]) else notes[21]
            13 -> if (((y * 5 / pY) <= 2) && ((x * 7 / pX) <= 1)) notes[22] else notes[23]

            else -> ""
        }

        

    }

    override fun toString(): String {
        return note
    }

    fun play(){
        try {
            var mediaPlayer = MediaPlayer.create(context, Uri.parse("android.resource://com.example.appinterfacesembarquees/raw/" + note))
            mediaPlayer.start()

        }catch (e: Exception){

        }
        //Toast.makeText(context, Uri.parse("android.resource://com.example.appinterfacesembarquees/raw/" + note).toString(), Toast.LENGTH_LONG).show()

    }






}

