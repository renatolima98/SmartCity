package ipvc.estg.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ipvc.estg.myapplication.entities.Notes

@Dao

interface NotesDAO {

    @Query("SELECT * FROM notes ORDER BY title ASC")
    fun getAlphabetizedNotes(): LiveData<List<Notes>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(notes: Notes)

    @Query("DELETE FROM notes")
    suspend fun deleteAll()
}