package com.example.notesappsaveonly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rv:RecyclerView
    lateinit var button: Button
    lateinit var etNote:EditText
    var note=""
    //var notes=ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv=findViewById(R.id.rv)
        button=findViewById(R.id.button)
        etNote=findViewById(R.id.etNote)

        button.setOnClickListener {
            note=etNote.text.toString()
            val dbHelper=DbHelper(applicationContext)
            val status=dbHelper.addNote(note)
            //notes.add(note)
           // rv.adapter=myAdapter(notes)
           // rv.layoutManager=LinearLayoutManager(this)
            Toast.makeText(applicationContext,"Note Added  "+status,Toast.LENGTH_LONG).show()
        }
    }
}