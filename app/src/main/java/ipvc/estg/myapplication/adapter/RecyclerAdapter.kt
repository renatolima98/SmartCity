package ipvc.estg.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ipvc.estg.myapplication.R
import ipvc.estg.myapplication.entities.Notes

class RecyclerAdapter internal constructor(context: Context, private val listenerDelete: DeleteClickListener, private val listenerEdit: EditClickListener) : RecyclerView.Adapter<RecyclerAdapter.NoteViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Notes>() // Cached copy of notes

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val noteTitleView: TextView = itemView.findViewById(R.id.Titulo)
        val noteNoteView: TextView = itemView.findViewById(R.id.Notatexto)
        val idNoteView: TextView = itemView.findViewById(R.id.idEscondido)

        val removeItemView: ImageButton = itemView.findViewById(R.id.imageDelete)
        val editItemView: ImageButton = itemView.findViewById(R.id.imageEdit)

        init {
            removeItemView.setOnClickListener(this)
            editItemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.imageDelete->{
                    listenerDelete.deleteClick(idNoteView.text.toString().toInt(),noteTitleView.text.toString())
                }
                R.id.imageEdit->{
                    listenerEdit.EditClick(idNoteView.text.toString().toInt(), noteTitleView.text.toString(), noteNoteView.text.toString())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.recycler, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val current = notes[position]
        holder.noteTitleView.text = current.title
        holder.noteNoteView.text = current.note
        holder.idNoteView.text = current.id.toString()
    }

    internal fun setNotes(notes: List<Notes>) {
        this.notes = notes
        notifyDataSetChanged()
    }


    override fun getItemCount() = notes.size

    interface DeleteClickListener{
        fun deleteClick(id :Int, titulo: String)
    }

    interface EditClickListener{
        fun EditClick(id:Int, titulo:String, notes:String)
    }
}