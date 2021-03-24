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
class Notes(instru: Int, private val notes: Array<String>, applicationContext: Context){

    /**liste des noms de notes*/
    var context = applicationContext
    /**tableau permettant d'accèder aux fichiers pour les instruments*/
    private val tabInstrument = listOf<String>("p","u","ba","b","e","m")
    private var instrument = tabInstrument[instru]
    private val sonNotes : MutableList<MediaPlayer> = mutableListOf()
    private var boolNotes : MutableList<Boolean> = mutableListOf()
    init {
        val notesIterator = notes.iterator()
        while (notesIterator.hasNext()) {
            /** création d'un MediaPlayer qui joue un fichier son en fonction de la note et de l'instrument */
            sonNotes.add(MediaPlayer.create(context, Uri.parse("android.resource://com.example.appinterfacesembarquees/raw/${notesIterator.next()}$instrument")))
            boolNotes.add(true)
        }
    }

    /**fonction qui retourne la liste de notes*/
    override fun toString(): String {
        return notes.toString()
    }

    /**fonction permettant de jouer une note en fonction de l'instrument et d'afficher la note correspondante en grisé sur l'écrand */
    fun play(note: Int){
        try {
            if(note <= notes.size) {
                if (!sonNotes[note].isPlaying) {
                    sonNotes[note].start()
                } else {
                    sonNotes[note].stop()
                    sonNotes[note].prepare()
                    sonNotes[note].start()
                }
            }
            boolNotes[note] = false
        } catch (e: Exception){
        }
    }

    /** Fonction pour changer d'instrument */
    fun changeInstrument(instru: Int){
        instrument = tabInstrument[instru]
        val sonIterator = sonNotes.iterator()
        val notesIterator = notes.iterator()
        while (sonIterator.hasNext()){
            sonIterator.next().release()
        }
        sonNotes.clear()
        while (notesIterator.hasNext()) {
            /** création d'un MediaPlayer qui joue un fichier son en fonction de la note et de l'instrument */
            sonNotes.add(MediaPlayer.create(context, Uri.parse("android.resource://com.example.appinterfacesembarquees/raw/${notesIterator.next()}$instrument")))
        }
    }

    fun setBoolNotes(i: Int, b: Boolean){
        boolNotes[i] = b
    }

    fun getBoolNotes(): MutableList<Boolean> {
        return boolNotes
    }

}

