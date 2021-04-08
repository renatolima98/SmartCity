package ipvc.estg.myapplication.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ipvc.estg.myapplication.DAO.NotesDAO
import ipvc.estg.myapplication.entities.Notes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Notes::class), version = 8, exportSchema = false)

public abstract class NotesDB : RoomDatabase() {

    abstract fun notesDao(): NotesDAO


    private class NotesDBCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var noteDAO = database.notesDao()

                }
            }
        }

    }
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NotesDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): NotesDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDB::class.java,
                    "Notes_DB"
                )
                    //estratégia de destruição

                    .addCallback(NotesDBCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}