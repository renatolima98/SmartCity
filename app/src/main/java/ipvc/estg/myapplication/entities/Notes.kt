package ipvc.estg.myapplication.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")

class Notes(

    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Note") val note: String

    )