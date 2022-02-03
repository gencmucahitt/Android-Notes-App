package com.works.notesexamplee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.works.notesexamplee.adapters.NotesAdapter
import com.works.notesexamplee.databinding.ActivityNotesListBinding

class NotesList : AppCompatActivity() {

    private lateinit var bind:ActivityNotesListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityNotesListBinding.inflate(layoutInflater)
        setContentView(bind.root)
        val db=DB(this)
        val datas = db.allNotes()
        bind.notesList.adapter=NotesAdapter(datas)

    }

}