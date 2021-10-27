package com.example.notesappsaveonly

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.item_row.view.*

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var button: Button
    lateinit var etNote:EditText
    lateinit var dbHelper:DbHelper
    var note=""
    var notes=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv)
        button=findViewById(R.id.button)
        etNote=findViewById(R.id.etNote)
        dbHelper=DbHelper(applicationContext)

       recyclerView()

        button.setOnClickListener {
            note=etNote.text.toString()
            val status=dbHelper.addNote(note)
            recyclerView()
            Toast.makeText(applicationContext,"Note Added  "+status,Toast.LENGTH_LONG).show()
        }


    }

    fun recyclerView(){
        notes=dbHelper.retrieveNotes()
        rv.adapter=myAdapter(notes,this)
        rv.layoutManager=LinearLayoutManager(this)
    }


}