package com.works.notesexamplee

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.works.notesexamplee.databinding.ActivityNotesDetailsBinding

class NotesDetails : AppCompatActivity() {
    lateinit var db:DB
    var uid:String?=null
    private lateinit var bind:ActivityNotesDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityNotesDetailsBinding.inflate(layoutInflater)
        setContentView(bind.root)
        db=DB(this)
        uid=intent.getStringExtra("uid")
        val title =intent.getStringExtra("title")
        val description = intent.getStringExtra("description")
        val history =intent.getStringExtra("history")
        bind.apply {
            uTxtTitle.setText(title)
            uTxtDescription.setText(description)
            uTxtHistory.setText(history)
        }

        bind.btnUpdate.setOnClickListener {

            val title = bind.uTxtTitle.text.toString()
            val description = bind.uTxtDescription.text.toString()
            val history = bind.uTxtHistory.text.toString()
            val alert = AlertDialog.Builder(bind.root.context)
            alert.setTitle("Güncelleme İşlemi!")
            alert.setMessage("Güncellemek istediğinizden emin misiniz?")
            alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i -> })
            alert.setPositiveButton("Evet", DialogInterface.OnClickListener { dialogInterface, i ->
                val count = db.notesUpdate(uid!!, title, description, history)
                Toast.makeText(this, "Düzeltme Başarılı", Toast.LENGTH_SHORT).show()
                val i = Intent(this, NotesList::class.java)
                startActivity(i)
            }).show()




        }


    }
}