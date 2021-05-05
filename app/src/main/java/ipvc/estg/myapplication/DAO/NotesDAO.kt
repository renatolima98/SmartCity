package ipvc.estg.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.myapplication.entities.Notes

@Dao

interface NotesDAO {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    // Mudei a query para aparecerem as notas por ordem de criação e nao alfabetica
    fun getAlphabetizedNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()

    @Query("DELETE FROM notes where id = :id")
    suspend fun deleteNote(id: Int)

}