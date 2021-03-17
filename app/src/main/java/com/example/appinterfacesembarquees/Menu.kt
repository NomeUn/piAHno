package com.example.appinterfacesembarquees

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val theme = findViewById<Button>(R.id.theme)
        theme.setOnClickListener{
            Toast.makeText(this, "coucou", Toast.LENGTH_SHORT).show()
        }

        val enregistrement = findViewById<Button>(R.id.enregistrement)
        enregistrement.setOnClickListener{
            Toast.makeText(this, "slt", Toast.LENGTH_SHORT).show()
        }

        val paypal = findViewById<Button>(R.id.paypal)
        paypal.setOnClickListener{
            val url = resources.getString(R.string.Paypal)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}