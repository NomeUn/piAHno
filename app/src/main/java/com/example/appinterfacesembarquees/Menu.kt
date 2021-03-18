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

        /** Controlleur sur le bouton theme */
        val themes = resources.getStringArray(R.array.Themes)
        val theme = findViewById<Spinner>(R.id.spnTheme)
        /** On affiche le theme que l'utilisateur a au moment où il clique sur le menu */
        val defaut = intent.getIntExtra("theme", 0)
        intent.removeExtra("theme")
        if (theme != null) {
            /** Création de l'adapteur pour le spinner */
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, themes)
            theme.adapter = adapter
            theme.onItemSelectedListener = this@Menu
            theme.setSelection(defaut)
        }

        /** Controlleur sur le bouton enregistrement */
        val enregistrement = findViewById<Button>(R.id.enregistrement)
        enregistrement.setOnClickListener{
            /** On crée une nouvelle activité avec le chemin jusqu'au cache de l'application */
            val intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            val uri = Uri.parse("${externalCacheDir?.absoluteFile}/")
            intent.setDataAndType(uri, "*/*")
            startActivity(Intent.createChooser(intent, "Open folder"))
        }

        /** Controlleur sur le bouton paypal */
        val paypal = findViewById<Button>(R.id.paypal)
        paypal.setOnClickListener{
            /** On crée une nouvelle activité qui va directement sur le site paypal */
            /** N'hésitez pas à donner ça fait plaisir */
            val url = resources.getString(R.string.Paypal)
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    /** Fonction quand l'utilisateur sélectionne un theme */
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        intent.removeExtra("theme")
        /** On passe l'information du theme via le intent qui avait permis d'ouvrir le menu */
        setResult(RESULT_OK, intent.putExtra("theme", position))
    }

    /** Fonction quand l'utilisateur ne sélectionne pas de theme */
    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(this, "Vous avez rien pris", Toast.LENGTH_SHORT).show()
    }
}