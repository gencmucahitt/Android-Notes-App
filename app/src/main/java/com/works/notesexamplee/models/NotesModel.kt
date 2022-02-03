package com.works.notesexamplee.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class NotesModel : RealmObject() {

    @PrimaryKey
    var uid:String = ""

    var title:String=""
    var description:String=""
    var history:String=""
}