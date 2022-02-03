package com.works.notesexamplee

import android.content.Context
import com.works.notesexamplee.models.NotesModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import java.util.*

class DB ( val context: Context) {
    fun config() : Realm?{
        Realm.init(context)
        val conf = RealmConfiguration.Builder()
            .name("node.db")
            .schemaVersion(1)
            .allowQueriesOnUiThread(true)
            .allowWritesOnUiThread(true)
            .build()
        return Realm.getInstance(conf)
    }

    fun notesInsert(title: String, description: String,history:String){
        val note = NotesModel()
        note.uid = UUID.randomUUID().toString()
        note.title=title
        note.description = description
        note.history=history
        config()?.executeTransaction {
                it.insert(note)
            }
    }
    fun allNotes() :ArrayList<NotesModel>{
        val list = ArrayList<NotesModel>()
        config()?.executeTransaction {
            it.where<NotesModel>().findAll().forEach {
                list.add(it)
            }
        }
        return list
    }
    fun deleteNotes(uid:String) {
        val notes = config()?.where<NotesModel>()
            ?.equalTo("uid", uid)
            ?.findFirst()

        config()?.executeTransaction {
            notes!!.deleteFromRealm()
        }
        }

   fun notesUpdate(uid:String,title: String,description: String,history: String){
        val list = NotesModel()
        config()?.executeTransaction {
            list.uid=uid
            list.title = title
            list.description=description
            list.history= history
            it.insertOrUpdate(list)
        }
    }




}