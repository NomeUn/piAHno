package com.example.appinterfacesembarquees

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivPiano = findViewById<ImageView>(R.id.ivPiano)
        val tvX = findViewById<TextView>(R.id.tvX)
        val tvY = findViewById<TextView>(R.id.tvY)


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
                tvX.text = Notes(note).toString()
            }else if (id == 1){
                tvY.text = note.toString()
                tvX.text = Notes(note).toString()
            }else if (id == 2){
                tvY.text = note.toString()
                tvX.text = Notes(note).toString()
            }
        }
    }


}

