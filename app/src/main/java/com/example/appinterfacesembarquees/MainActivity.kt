package com.example.appinterfacesembarquees

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.opengl.Visibility
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.annotation.DrawableRes
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
    lateinit var ivFaD1:ImageView
    lateinit var ivSol1: ImageView
    lateinit var ivSolD1: ImageView
    lateinit var ivLa1: ImageView
    lateinit var ivLaD1: ImageView
    lateinit var ivSi1: ImageView
    lateinit var ivDo1: ImageView
    lateinit var ivDoD1: ImageView
    lateinit var ivRe1: ImageView
    lateinit var ivReD1: ImageView
    lateinit var ivMi1: ImageView
    lateinit var ivFa2: ImageView
    lateinit var ivFaD2:ImageView
    lateinit var ivSol2: ImageView
    lateinit var ivSolD2: ImageView
    lateinit var ivLa2: ImageView
    lateinit var ivLaD2: ImageView
    lateinit var ivSi2: ImageView
    lateinit var ivDo2: ImageView
    lateinit var ivDoD2: ImageView
    lateinit var ivRe2: ImageView
    lateinit var ivReD2: ImageView
    lateinit var ivMi2: ImageView
    lateinit var metronome: ImageButton

    lateinit var tab: List<ImageView>

    lateinit var tvX: TextView
    lateinit var tvY: TextView
    lateinit var btnRec: Button
    var recorder: MediaRecorder? = null

    var tempo : Int = 0
    var timer = Timer()

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
            if (tempo != null && tempo != 0){
                var bpm : Long = (60000 / tempo).toLong()
                var metronomeTimer = MetronomeTimerTask()
                timer = Timer()
                metronomeTimer.context(this)
                timer.schedule(metronomeTimer, 0,  bpm)
                //metronomeTimer.run()
            }
            metronome.setBackgroundColor(Color.RED)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        metronome = findViewById<ImageButton>(R.id.metronome)
        metronome.setBackgroundColor(Color.GRAY)
        metronome.setOnClickListener {
            ivPiano.setImageResource(R.drawable.piano2)
            if (tempo == 0) {
                Intent(this, Metronome::class.java).also {
                    it.putExtra("tempo", tempo)
                    startActivityForResult(it, 1)
                }

            } else {
                tempo = 0
                timer.cancel()
                timer.purge()

                metronome.setBackgroundColor(Color.GRAY)

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
        ivFaD1 = findViewById<ImageView>(R.id.ivFaD1)
        ivFaD1.visibility = View.INVISIBLE
        ivSol1 = findViewById<ImageView>(R.id.ivSol1)
        ivSol1.visibility = View.INVISIBLE
        ivSolD1 = findViewById<ImageView>(R.id.ivSolD1)
        ivSolD1.visibility = View.INVISIBLE
        ivLa1 = findViewById<ImageView>(R.id.ivLa1)
        ivLa1.visibility = View.INVISIBLE
        ivLaD1 = findViewById<ImageView>(R.id.ivLaD1)
        ivLaD1.visibility = View.INVISIBLE
        ivSi1 = findViewById<ImageView>(R.id.ivSi1)
        ivSi1.visibility = View.INVISIBLE
        ivDo1 = findViewById<ImageView>(R.id.ivDo1)
        ivDo1.visibility = View.INVISIBLE
        ivDoD1 = findViewById<ImageView>(R.id.ivDoD1)
        ivDoD1.visibility = View.INVISIBLE
        ivRe1 = findViewById<ImageView>(R.id.ivRe1)
        ivRe1.visibility = View.INVISIBLE
        ivReD1 = findViewById<ImageView>(R.id.ivReD1)
        ivReD1.visibility = View.INVISIBLE
        ivMi1 = findViewById<ImageView>(R.id.ivMi1)
        ivMi1.visibility = View.INVISIBLE
        ivFa2 = findViewById<ImageView>(R.id.ivFa2)
        ivFa2.visibility = View.INVISIBLE
        ivFaD2 = findViewById<ImageView>(R.id.ivFaD2)
        ivFaD2.visibility = View.INVISIBLE
        ivSol2 = findViewById<ImageView>(R.id.ivSol2)
        ivSol2.visibility = View.INVISIBLE
        ivSolD2 = findViewById<ImageView>(R.id.ivSolD2)
        ivSolD2.visibility = View.INVISIBLE
        ivLa2 = findViewById<ImageView>(R.id.ivLa2)
        ivLa2.visibility = View.INVISIBLE
        ivLaD2 = findViewById<ImageView>(R.id.ivLaD2)
        ivLaD2.visibility = View.INVISIBLE
        ivSi2 = findViewById<ImageView>(R.id.ivSi2)
        ivSi2.visibility = View.INVISIBLE
        ivDo2 = findViewById<ImageView>(R.id.ivDo2)
        ivDo2.visibility = View.INVISIBLE
        ivDoD2 = findViewById<ImageView>(R.id.ivDoD2)
        ivDoD2.visibility = View.INVISIBLE
        ivRe2 = findViewById<ImageView>(R.id.ivRe2)
        ivRe2.visibility = View.INVISIBLE
        ivReD2 = findViewById<ImageView>(R.id.ivReD2)
        ivReD2.visibility = View.INVISIBLE
        ivMi2 = findViewById<ImageView>(R.id.ivMi2)
        ivMi2.visibility = View.INVISIBLE


        tab = listOf<ImageView>(ivFa1,ivFaD1,ivSol1,ivSolD1,ivLa1,ivLaD1,ivSi1,ivDo1,ivDoD1,ivRe1,ivReD1,ivMi1,ivFa2,ivFaD2,ivSol2,ivSolD2,ivLa2,ivLaD2,ivSi2,ivDo2,ivDoD2,ivRe2,ivReD2,ivMi2)

        btnRec = findViewById<Button>(R.id.btnRec)
        var mStartRecording = true

        ivPiano.setOnTouchListener {
            _, event ->
            handleTouch(event)
            true
        }

        btnRec.setOnClickListener{
            _, ->

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
            val x = m.getX(i).toInt()
            val y = m.getY(i).toInt()
            val id = m.getPointerId(i)
            val pX = ivPiano.width
            val pY = ivPiano.height

            var note = Notes(x, y, pX, pY, applicationContext, tab)

            if (id == 0) {
                note.play()
            } else if (id == 1) {
                note.play()
            } else if (id == 2) {
                note.play()
            }
        }
    }
}

