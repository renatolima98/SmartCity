package ipvc.estg.myapplication.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ipvc.estg.myapplication.DB.NotesDB
import ipvc.estg.myapplication.DB.NotesRepository
import ipvc.estg.myapplication.entities.Notes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NotesRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNotes: LiveData<List<Notes>>

    init {
        val notesDAO = NotesDB.getDatabase(application, viewModelScope).notesDao()
        repository = NotesRepository(notesDAO)
        allNotes = repository.allNotes
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(notes: Notes) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(notes)
    }

    fun deleteNote(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteNote(id)
    }

    fun updateNote(id:Int, titulo:String, notes:String) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(id, titulo, notes)
    }

}