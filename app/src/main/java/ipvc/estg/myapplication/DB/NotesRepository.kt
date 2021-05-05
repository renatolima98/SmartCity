package ipvc.estg.myapplication.DB


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import ipvc.estg.myapplication.DAO.NotesDAO
import ipvc.estg.myapplication.entities.Notes



class NotesRepository(private val notesDAO: NotesDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allNotes: LiveData<List<Notes>> = notesDAO.getAlphabetizedNotes()


    suspend fun insert(notes: Notes) {
        notesDAO.insert(notes)
    }


    suspend fun deleteNote(id: Int) {
        notesDAO.deleteNote(id)
    }



}