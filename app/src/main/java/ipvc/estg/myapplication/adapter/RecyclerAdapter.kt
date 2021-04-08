package ipvc.estg.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.myapplication.R
import ipvc.estg.myapplication.entities.Notes

class RecyclerAdapter internal constructor(context: Context) : RecyclerView.Adapter<RecyclerAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Notes>() // Cached copy of cities

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val noteTitleView: TextView = itemView.findViewById(R.id.Titulo)
        val noteNoteView: TextView = itemView.findViewById(R.id.Notatexto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recycler, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.noteTitleView.text = current.title
        holder.noteNoteView.text = current.note
    }

    internal fun setNotes(notes: List<Notes>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = notes.size

}