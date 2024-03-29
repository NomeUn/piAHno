package com.example.appinterfacesembarquees

import android.content.ContentResolver
import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File

/**
 * classe Notes
 * Elle permet de gérer tout ce quei est en rapport avec les notes
 * elle prend en paramètre:
 *  la position de notre doigt sur l'image View du piano en x et y
 *  les dimensions pX et pY du piano.
 *  le context de l'application
 *  la liste des images de touche
 *
 */
class Notes(val x: Int, val y: Int, val pX: Int, val pY: Int, applicationContext: Context, list: List<ImageView>){
    /**liste des noms de notes*/
    val notes = arrayOf("fa", "fad", "sol", "sold", "la", "lad", "si", "do", "dod", "re", "red", "mi", "fa2", "fad2", "sol2", "sold2", "la2", "lad2", "si2", "do2", "dod2", "re2", "red2", "mi2")
    val listeView = list
    var note = ""
    var context = applicationContext
    /**tableau permettant d'accèder aux fichiers pour les instruments*/
    val tab = listOf<String>("p","u","ba","b","e","m")

    init {
        /**produit en croix pour connaître la note correspondante en fonction de la position de notre doigt sur l'image du piano*/
        var num = x*14/pX

        /**calcul pour déterminer si la note sur laquelle on clique est une note noir ou non*/
        note = when(num){
            0 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 >= 4)) notes[1] else notes[0]
            1 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[1] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[3] else notes[2]) else notes[2]
            2 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[3] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[5] else notes[4]) else notes[4]
            3 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 <= 1)) notes[5] else notes[6]

            4 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 >= 4)) notes[8] else notes[7]
            5 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[8] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[10] else notes[9]) else notes[9]
            6 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 <= 1)) notes[10] else notes[11]

            7 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 >= 4)) notes[13] else notes[12]
            8 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[13] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[15] else notes[14]) else notes[14]
            9 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[15] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[17] else notes[16]) else notes[16]
            10 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 <= 1)) notes[17] else notes[18]

            11 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 >= 4)) notes[20] else notes[19]
            12 -> if ((y * 5 / pY) <= 2) (if ((x * 7 * 14 / pX) % 7 <= 1) notes[20] else if ((x * 7 * 14 / pX) % 7 >= 4) notes[22] else notes[21]) else notes[21]
            13 -> if (((y * 5 / pY) <= 2) && ((x * 7 * 14 / pX) % 7 <= 1)) notes[22] else notes[23]

            else -> ""
        }

        

    }

    /**fonction qui retourne le nom de la note*/
    override fun toString(): String {
        return note
    }

    /**fonction permettant de jouer une note en fonction de l'instrument et d'afficher la note correspondante en grisé sur l'écrand */
    fun play(instru: Int){
        try {
            /** création d'un MediaPlayer qui joue un fichier son en fonction de la note et de l'instrument */
            var mediaPlayer = MediaPlayer.create(context, Uri.parse("android.resource://com.example.appinterfacesembarquees/raw/" + note + tab[instru]))
            mediaPlayer.start()
            /** On release le mediaPlayer lorsque le son est fini */
            mediaPlayer.setOnCompletionListener { mediaPlayer ->
                try {
                    mediaPlayer.release()
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                }
            }

            /**on affecte à la variable noteView l'imageView correspondant à la note choisie et on la rend visible pendant un certains laps de temps puis on la remet en invisible*/
            var noteView = listeView[notes.indexOf(note)]
            noteView.visibility = View.VISIBLE
            GlobalScope.launch {
                delay(100L)
                noteView.visibility = View.INVISIBLE
            }

        }catch (e: Exception){

        }

    }

}

