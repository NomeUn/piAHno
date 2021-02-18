package com.example.appinterfacesembarquees

import android.media.MediaPlayer
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import java.lang.Thread.sleep
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    lateinit var ivPiano: ImageView
    lateinit var ivFa1: ImageView
    lateinit var ivSol1: ImageView
    lateinit var ivLa1: ImageView
    lateinit var ivSi1: ImageView
    lateinit var ivDo1: ImageView
    lateinit var ivRe1: ImageView
    lateinit var ivMi1: ImageView
    lateinit var ivFa2: ImageView
    lateinit var ivSol2: ImageView
    lateinit var ivLa2: ImageView
    lateinit var ivSi2: ImageView
    lateinit var ivDo2: ImageView
    lateinit var ivRe2: ImageView
    lateinit var ivMi2: ImageView
    lateinit var tvX: TextView
    lateinit var tvY: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivPiano = findViewById<ImageView>(R.id.ivPiano)

        ivFa1 = findViewById<ImageView>(R.id.ivFa1)
        ivFa1.visibility = View.INVISIBLE
        ivSol1 = findViewById<ImageView>(R.id.ivSol1)
        ivSol1.visibility = View.INVISIBLE
        ivLa1 = findViewById<ImageView>(R.id.ivLa1)
        ivLa1.visibility = View.INVISIBLE
        ivSi1 = findViewById<ImageView>(R.id.ivSi1)
        ivSi1.visibility = View.INVISIBLE
        ivDo1 = findViewById<ImageView>(R.id.ivDo1)
        ivDo1.visibility = View.INVISIBLE
        ivRe1 = findViewById<ImageView>(R.id.ivRe1)
        ivRe1.visibility = View.INVISIBLE
        ivMi1 = findViewById<ImageView>(R.id.ivMi1)
        ivMi1.visibility = View.INVISIBLE
        ivFa2 = findViewById<ImageView>(R.id.ivFa2)
        ivFa2.visibility = View.INVISIBLE
        ivSol2 = findViewById<ImageView>(R.id.ivSol2)
        ivSol2.visibility = View.INVISIBLE
        ivLa2 = findViewById<ImageView>(R.id.ivLa2)
        ivLa2.visibility = View.INVISIBLE
        ivSi2 = findViewById<ImageView>(R.id.ivSi2)
        ivSi2.visibility = View.INVISIBLE
        ivDo2 = findViewById<ImageView>(R.id.ivDo2)
        ivDo2.visibility = View.INVISIBLE
        ivRe2 = findViewById<ImageView>(R.id.ivRe2)
        ivRe2.visibility = View.INVISIBLE
        ivMi2 = findViewById<ImageView>(R.id.ivMi2)
        ivMi2.visibility = View.INVISIBLE


       ivPiano.setOnTouchListener {
                _, event ->
            handleTouch(event)
            true
        }

        val instruments = resources.getStringArray(R.array.Instruments)
        val spinner = findViewById<Spinner>(R.id.spnInstru)
        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, instruments)
            spinner.adapter = adapter
        }
    }

    private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount

            for (i in 0 until pointerCount) {
                val x = m.getX(i).toInt()
                val y = m.getY(i).toInt()
                val id = m.getPointerId(i)
                val pX = ivPiano.width.toInt()
                val pY = ivPiano.height.toInt()

                //var note = (x.toInt()) * 14 / (ivPiano.width.toInt())
                var note = (x, y, pX, pY)
                if (id == 0) {
                    var nomNote = Notes(note).toString()
                    when (nomNote) {
                        "Fa" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa1)
                            mediaPlayer.start()
                            ivFa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa1.visibility = View.INVISIBLE
                            }

                        }
                        "Sol" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol1)
                            mediaPlayer.start()
                            ivSol1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol1.visibility = View.INVISIBLE
                            }
                        }
                        "La" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la1)
                            mediaPlayer.start()
                            ivLa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa1.visibility = View.INVISIBLE
                            }
                        }
                        "Si" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si1)
                            mediaPlayer.start()
                            ivSi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi1.visibility = View.INVISIBLE
                            }

                        }
                        "Do" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do1)
                            mediaPlayer.start()
                            ivDo1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo1.visibility = View.INVISIBLE
                            }

                        }
                        "Re" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re1)
                            mediaPlayer.start()
                            ivRe1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe1.visibility = View.INVISIBLE
                            }

                        }
                        "Mi" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi1)
                            mediaPlayer.start()
                            ivMi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi1.visibility = View.INVISIBLE
                            }
                        }
                        "Fa2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa2)
                            mediaPlayer.start()
                            ivFa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa2.visibility = View.INVISIBLE
                            }
                        }
                        "Sol2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol2)
                            mediaPlayer.start()
                            ivSol2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol2.visibility = View.INVISIBLE
                            }
                        }
                        "La2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la2)
                            mediaPlayer.start()
                            ivLa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa2.visibility = View.INVISIBLE
                            }
                        }
                        "Si2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si2)
                            mediaPlayer.start()
                            ivSi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi2.visibility = View.INVISIBLE
                            }

                        }
                        "Do2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do2)
                            mediaPlayer.start()
                            ivDo2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo2.visibility = View.INVISIBLE
                            }

                        }
                        "Re2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re2)
                            mediaPlayer.start()
                            ivRe2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe2.visibility = View.INVISIBLE
                            }

                        }
                        "Mi2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi2)
                            mediaPlayer.start()
                            ivMi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi2.visibility = View.INVISIBLE
                            }

                        }
                        else -> {
                        }
                    }

                } else if (id == 1) {
                    tvY.text = note.toString()
                    var nomNote = Notes(note).toString()
                    tvX.text = Notes(note).toString()
                    when (nomNote) {
                        "Fa" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa1)
                            mediaPlayer.start()
                            ivFa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa1.visibility = View.INVISIBLE
                            }

                        }
                        "Sol" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol1)
                            mediaPlayer.start()
                            ivSol1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol1.visibility = View.INVISIBLE
                            }
                        }
                        "La" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la1)
                            mediaPlayer.start()
                            ivLa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa1.visibility = View.INVISIBLE
                            }
                        }
                        "Si" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si1)
                            mediaPlayer.start()
                            ivSi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi1.visibility = View.INVISIBLE
                            }

                        }
                        "Do" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do1)
                            mediaPlayer.start()
                            ivDo1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo1.visibility = View.INVISIBLE
                            }

                        }
                        "Re" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re1)
                            mediaPlayer.start()
                            ivRe1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe1.visibility = View.INVISIBLE
                            }

                        }
                        "Mi" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi1)
                            mediaPlayer.start()
                            ivMi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi1.visibility = View.INVISIBLE
                            }
                        }
                        "Fa2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa2)
                            mediaPlayer.start()
                            ivFa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa2.visibility = View.INVISIBLE
                            }
                        }
                        "Sol2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol2)
                            mediaPlayer.start()
                            ivSol2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol2.visibility = View.INVISIBLE
                            }
                        }
                        "La2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la2)
                            mediaPlayer.start()
                            ivLa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa2.visibility = View.INVISIBLE
                            }
                        }
                        "Si2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si2)
                            mediaPlayer.start()
                            ivSi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi2.visibility = View.INVISIBLE
                            }

                        }
                        "Do2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do2)
                            mediaPlayer.start()
                            ivDo2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo2.visibility = View.INVISIBLE
                            }

                        }
                        "Re2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re2)
                            mediaPlayer.start()
                            ivRe2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe2.visibility = View.INVISIBLE
                            }

                        }
                        "Mi2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi2)
                            mediaPlayer.start()
                            ivMi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi2.visibility = View.INVISIBLE
                            }

                        }
                        else -> {
                        }
                    }
                } else if (id == 2) {
                    tvY.text = note.toString()
                    var nomNote = Notes(note).toString()
                    tvX.text = Notes(note).toString()
                    when (nomNote) {
                        "Fa" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa1)
                            mediaPlayer.start()
                            ivFa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa1.visibility = View.INVISIBLE
                            }

                        }
                        "Sol" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol1)
                            mediaPlayer.start()
                            ivSol1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol1.visibility = View.INVISIBLE
                            }
                        }
                        "La" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la1)
                            mediaPlayer.start()
                            ivLa1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa1.visibility = View.INVISIBLE
                            }
                        }
                        "Si" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si1)
                            mediaPlayer.start()
                            ivSi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi1.visibility = View.INVISIBLE
                            }

                        }
                        "Do" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do1)
                            mediaPlayer.start()
                            ivDo1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo1.visibility = View.INVISIBLE
                            }

                        }
                        "Re" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re1)
                            mediaPlayer.start()
                            ivRe1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe1.visibility = View.INVISIBLE
                            }

                        }
                        "Mi" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi1)
                            mediaPlayer.start()
                            ivMi1.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi1.visibility = View.INVISIBLE
                            }
                        }
                        "Fa2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.fa2)
                            mediaPlayer.start()
                            ivFa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivFa2.visibility = View.INVISIBLE
                            }
                        }
                        "Sol2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.sol2)
                            mediaPlayer.start()
                            ivSol2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSol2.visibility = View.INVISIBLE
                            }
                        }
                        "La2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.la2)
                            mediaPlayer.start()
                            ivLa2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivLa2.visibility = View.INVISIBLE
                            }
                        }
                        "Si2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.si2)
                            mediaPlayer.start()
                            ivSi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivSi2.visibility = View.INVISIBLE
                            }

                        }
                        "Do2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.do2)
                            mediaPlayer.start()
                            ivDo2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivDo2.visibility = View.INVISIBLE
                            }

                        }
                        "Re2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.re2)
                            mediaPlayer.start()
                            ivRe2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivRe2.visibility = View.INVISIBLE
                            }

                        }
                        "Mi2" -> {
                            var mediaPlayer = MediaPlayer.create(this, R.raw.mi2)
                            mediaPlayer.start()
                            ivMi2.visibility = View.VISIBLE
                            GlobalScope.launch {
                                delay(100L)
                                ivMi2.visibility = View.INVISIBLE
                            }
                        }
                    }
                }
            }
    }


}

