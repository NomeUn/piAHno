package com.example.appinterfacesembarquees

import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.opengl.Visibility
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.lang.Thread.sleep
import kotlinx.coroutines.*
import java.io.IOException
import java.util.jar.Manifest

import java.util.*

private const val LOG_TAG = "AudioRecordTest"


@Suppress("DEPRECATION")
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
    lateinit var btnRec: Button
    var recorder: MediaRecorder? = null

    var tempo : Int = 0
    var timer = Timer()
    var metronomeTimer = MetronomeTimerTask()

    private fun onRecord(start: Boolean) = if (start) {
        startRecording()
        btnRec.text = "STOP"
    } else {
        stopRecording()
        btnRec.text = "REC"
    }


    private fun startRecording() {
        recorder = MediaRecorder().apply {
            var output = "${externalCacheDir?.absolutePath}/audiorecordtest.3gp" //Environment.getExternalStorageDirectory().absolutePath + "/recording.3gp"
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(output)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try{
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){
            if (data != null) {
                tempo = data.getIntExtra("tempo", 0)
            }
            if (tempo != null){
                if(tempo != 0){
                    var bpm : Long = (60000 / tempo).toLong()

                    timer.schedule(metronomeTimer, 0,  bpm)
                    metronomeTimer.context(this)
                    //metronomeTimer.run()
                    Toast.makeText(this, bpm.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val metronome = findViewById<Button>(R.id.metronome)
        metronome.setOnClickListener {
            if (tempo == 0) {
                Intent(this, Metronome::class.java).also {
                    it.putExtra("tempo", tempo)
                    startActivityForResult(it, 1)
                }
            } else {
                tempo = 0
                timer.cancel()
                Toast.makeText(this, "Arrêt du métronome", Toast.LENGTH_SHORT).show()
            }
        }

        val menu = findViewById<Button>(R.id.menu)
        menu.setOnClickListener {
            Intent(this, Menu::class.java).also {
                startActivity(it)
            }
        }

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

        btnRec = findViewById<Button>(R.id.btnRec)


       ivPiano.setOnTouchListener {
                _, event ->
            handleTouch(event)
            true
        }

        btnRec.setOnClickListener{
                _, ->

            var mStartRecording = true

            if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                val permissions = arrayOf(android.Manifest.permission.RECORD_AUDIO, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                ActivityCompat.requestPermissions(this, permissions,0)
            }else{
                onRecord(mStartRecording)
            }
            mStartRecording = !mStartRecording
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
                val x = m.getX(i)
                val y = m.getY(i)
                val id = m.getPointerId(i)

                var note = (x.toInt()) * 14 / (ivPiano.width.toInt())

                when (id) {
                    0 -> {
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

                    }
                    1 -> {
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
                    }
                    2 -> {
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


}

