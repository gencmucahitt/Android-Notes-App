package com.works.notesexamplee.adapters

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.works.notesexamplee.DB
import com.works.notesexamplee.NotesDetails
import com.works.notesexamplee.NotesList
import com.works.notesexamplee.databinding.NotesRowBinding
import com.works.notesexamplee.models.NotesModel

class NotesAdapter( val arr: ArrayList<NotesModel> ) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    class ViewHolder ( val bind: NotesRowBinding ) : RecyclerView.ViewHolder( bind.root  ) { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.ViewHolder {
        val bind =NotesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: NotesAdapter.ViewHolder, position: Int) {
        val item =arr.get(position)
        holder.bind.apply {
            rTxtTitle.setText(item.title)
            rTxtDescription.setText(item.description)
            rTxtHistory.setText(item.history)

            rCardView.setOnClickListener {
                val i = Intent(holder.bind.root.context, NotesDetails::class.java)
                i.putExtra("uid", item.uid)
                i.putExtra("title", item.title)
                i.putExtra("description", item.description)
                i.putExtra("history", item.history)
                notifyDataSetChanged()
                holder.bind.root.context.startActivity(i)
            }
            rCardView.setOnLongClickListener {
                val alert = AlertDialog.Builder(holder.bind.root.context)
                alert.setTitle("Silme İşlemi!")
                alert.setMessage("Silmek istediğinizden emin misiniz?")
                alert.setNegativeButton("İptal",DialogInterface.OnClickListener { dialogInterface, i -> })
                alert.setPositiveButton("Evet",DialogInterface.OnClickListener { dialogInterface, i ->
                        val db = DB(holder.bind.root.context)
                        db.deleteNotes(item.uid!!)
                        Toast.makeText(holder.bind.root.context,"Silme işlemi başarılı!",
                            Toast.LENGTH_SHORT
                        ).show()
                        arr.removeAt(position)
                        notifyItemRemoved(position)
                    })
                alert.show()

                true
            }
        }

        }

    override fun getItemCount(): Int {
        return arr.size
    }
}