package com.works.notesexamplee
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.works.notesexamplee.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    lateinit var db: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        db=DB(this)

        bind.btnAdd.setOnClickListener {
            val title = bind.txtTitle.text.toString()
            if(title.isEmpty()){
                bind.txtTitle.error ="Başlık Girişi Yapılmadı"
                bind.txtTitle.requestFocus()
                return@setOnClickListener
            }
            val description =bind.txtDescription.text.toString()
            if(description.isEmpty()){
                bind.txtDescription.error ="Detay Girişi Yapılmadı"
                bind.txtDescription.requestFocus()
                return@setOnClickListener
            }
            val history =bind.txtHistory.text.toString()
            if(history.isEmpty()){
                bind.txtHistory.error ="Tarih Belirtmediniz"
                bind.txtHistory.requestFocus()
                return@setOnClickListener
            }
            val alert = AlertDialog.Builder(bind.root.context)
            alert.setTitle("Ekleme İşlemi!")
            alert.setMessage("Eklemek istediğinizden emin misiniz?")
            alert.setNegativeButton("İptal", DialogInterface.OnClickListener { dialogInterface, i -> })
            alert.setPositiveButton("Evet", DialogInterface.OnClickListener{dialogInterface, i ->
                val count =db.notesInsert(title,description,history)
                Toast.makeText(bind.root.context,"Ekleme işlemi başarılı!",
                    Toast.LENGTH_SHORT
                ).show()
            }).show()
            }

            bind.btnGotoList.setOnClickListener {
                val i = Intent(this,NotesList::class.java)
                startActivity(i)
            }
        }




    }

