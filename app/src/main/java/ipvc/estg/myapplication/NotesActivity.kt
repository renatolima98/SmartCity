package ipvc.estg.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ipvc.estg.myapplication.ViewModel.NotesViewModel
import ipvc.estg.myapplication.adapter.RecyclerAdapter
import ipvc.estg.myapplication.entities.Notes
import java.lang.Exception

class NotesActivity : AppCompatActivity() {
    private lateinit var noteViewModel: NotesViewModel
    private val newNoteActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        val floatbtn = findViewById<FloatingActionButton>(R.id.floatbtn)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = RecyclerAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        noteViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer { notes ->
            notes?.let{ adapter.setNotes(it)}
        })

        floatbtn.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
        try {
            val title = intent.getStringExtra("title")
            val text = intent.getStringExtra("note")

            val note = Notes(title = title,note = text)

            noteViewModel.insert(note)

        } catch (e: Exception) {

            Log.i("RIP","errou")
        }

    }






}