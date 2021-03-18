package com.example.appinterfacesembarquees

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Metronome : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_metronome)

        val tempo = intent.getIntExtra("tempo", 60)
        intent.removeExtra("tempo")

        /** Controlleur sur le number picker */
        val numberPicker = findViewById<NumberPicker>(R.id.numberPicker)
        if (numberPicker != null) {
            numberPicker.minValue = 0
            numberPicker.maxValue = 200
            numberPicker.value = 60
            numberPicker.wrapSelectorWheel = true
        }

        /** Controlleur sur le bouton valider */
        val valider = findViewById<Button>(R.id.valider)
        valider.setOnClickListener{
            /** On passe l'information du theme via le intent qui avait permis d'ouvrir le menu du metronome */
            val text = "Le tempo est de " + numberPicker.value
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK, intent.putExtra("tempo", numberPicker.value))
            finish()
        }
    }
}