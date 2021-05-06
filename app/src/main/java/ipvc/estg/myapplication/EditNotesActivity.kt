package ipvc.estg.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ipvc.estg.myapplication.ViewModel.NotesViewModel

class EditNotesActivity : AppCompatActivity() {

    private lateinit var titleNoteView: EditText
    private lateinit var NoteNoteView: EditText
    private lateinit var noteViewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_notes)



        titleNoteView = findViewById(R.id.Titulo)
        NoteNoteView = findViewById(R.id.nota)

        titleNoteView.setText(intent.getStringExtra("Titulo"))
        NoteNoteView.setText(intent.getStringExtra("Nota"))

        val editBtn = findViewById<Button>(R.id.editnota)

        noteViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        editBtn.setOnClickListener {

            val tituloString = titleNoteView.text.toString()
            val notaString = NoteNoteView.text.toString()
            val idString = intent.getIntExtra("id",0)

            if(tituloString.isEmpty() || notaString.isEmpty()){

                Toast.makeText(this,R.string.AddNoteError, Toast.LENGTH_SHORT).show()
            }
            else {

                Toast.makeText(this,R.string.editNote, Toast.LENGTH_SHORT).show()

                noteViewModel.updateNote(idString,tituloString,notaString)

                finish()
            }

        }
    }
}