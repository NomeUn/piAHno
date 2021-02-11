package com.example.appinterfacesembarquees

import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {


    lateinit var ivPiano: ImageView
    lateinit var tvX: TextView
    lateinit var tvY: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivPiano = findViewById<ImageView>(R.id.ivPiano)
        tvX = findViewById<TextView>(R.id.tvX)
        tvY = findViewById<TextView>(R.id.tvY)


        ivPiano.setOnTouchListener {
                _, event ->
            handleTouch(event)
            true
        }




    }

        private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount



        for (i in 0 until pointerCount) {
            val x = m.getX(i)
            val y = m.getY(i)
            val id = m.getPointerId(i)

            var note = (x.toInt())*14/(ivPiano.width.toInt())

            if (id == 0){
                tvY.text = note.toString()
                var nomNote = Notes(note).toString()
                tvX.text = Notes(note).toString()
                when(nomNote){
                    "Fa" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano11)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                        /*Thread().run{
                            findViewById<ImageView>(R.id.ivFa1).visibility = View.VISIBLE
                            Thread.sleep(1000)
                            findViewById<ImageView>(R.id.ivFa1).visibility = View.INVISIBLE
                        }*/

                    }
                    "Sol" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano110)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    "La" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano111)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    else -> {}
                }

            }else if (id == 1){
                tvY.text = note.toString()
                var nomNote = Notes(note).toString()
                tvX.text = Notes(note).toString()
                when(nomNote){
                    "Fa" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano11)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    "Sol" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano110)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    "La" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano111)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    else -> {}
                }
            }else if (id == 2){
                tvY.text = note.toString()
                var nomNote = Notes(note).toString()
                tvX.text = Notes(note).toString()
                when(nomNote){
                    "Fa" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano11)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    "Sol" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano110)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    "La" ->{
                        var mediaPlayer = MediaPlayer.create(this, R.raw.piano111)
                        mediaPlayer.start() // no need to call prepare(); create() does that for you
                    }
                    else -> {}
                }
            }
        }
    }


}

