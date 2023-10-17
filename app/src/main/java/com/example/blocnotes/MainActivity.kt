package com.example.blocnotes

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var noteEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var notesTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        noteEditText = findViewById(R.id.noteEditText)
        saveButton = findViewById(R.id.saveButton)
        notesTextView = findViewById(R.id.notesTextView)

        // Charger la note précédemment sauvegardée
        val sharedPreferences: SharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE)
        val savedNote: String? = sharedPreferences.getString("user_note", "")
        noteEditText.setText(savedNote)

        saveButton.setOnClickListener {
            // Récupérer le contenu du champ texte
            val note: String = noteEditText.text.toString()

            // Récupérer les notes précédemment sauvegardées
            val previousNotes: String = notesTextView.text.toString()

            // Mettre à jour le TextView avec les notes précédentes plus la nouvelle note
            val combinedNotes: String = previousNotes + "\n" + note
            notesTextView.text = combinedNotes

            // Enregistrer la note dans les préférences partagées
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user_note", note)
            editor.apply()
        }
    }
}
