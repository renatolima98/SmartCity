package ipvc.estg.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddNoteActivity : AppCompatActivity() {

    private lateinit var titleNoteView: EditText
    private lateinit var NoteNoteView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        titleNoteView = findViewById(R.id.Titulo)
        NoteNoteView = findViewById(R.id.nota)

        val button = findViewById<Button>(R.id.addnota)
        button.setOnClickListener {

                val replyIntent = Intent(this, NotesActivity::class.java)
                val text = NoteNoteView.text.toString()
                val title = titleNoteView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY_title, title)
                replyIntent.putExtra(EXTRA_REPLY_note, text)
                replyIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(replyIntent)
                finish()
        }
    }
    companion object{
        const val EXTRA_REPLY_title = "title"
        const val EXTRA_REPLY_note = "note"
    }
}