package com.example.notesappsaveonly

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) :SQLiteOpenHelper(context,"userNotes.db",null,1) {
    val SQLiteDatabase:SQLiteDatabase=writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            db.execSQL("create table Notes(Note text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addNote(n:String): Long {
        val cv=ContentValues()
        cv.put("Note",n)
        var status=SQLiteDatabase.insert("Notes",null,cv)
        return status
    }
}