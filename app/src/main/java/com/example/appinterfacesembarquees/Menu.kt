package com.example.appinterfacesembarquees

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class Menu : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val themes = resources.getStringArray(R.array.Themes)
        val theme = findViewById<Spinner>(R.id.spnTheme)
        if (theme != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, themes)
            theme.adapter = adapter
            theme.onItemSelectedListener = this@Menu
        }

        val enregistrement = findViewById<Button>(R.id.enregistrement)
        enregistrement.setOnClickListener{
            Toast.makeText(this, "slt", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val uri = Uri.parse("${externalCacheDir?.absoluteFile}/")
            intent.setDataAndType(uri, "*/*")
            startActivity(Intent.createChooser(intent, "Open folder"))
        }

        val paypal = findViewById<Button>(R.id.paypal)
        paypal.setOnClickListener{
            val url = resources.getString(R.string.Paypal)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        intent.removeExtra("theme")
        setResult(RESULT_OK, intent.putExtra("theme", position))
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "Vous avez rien pris", Toast.LENGTH_SHORT).show()
    }
}