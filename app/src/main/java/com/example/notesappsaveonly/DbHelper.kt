package com.example.notesappsaveonly

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
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

    fun retrieveNotes():ArrayList<String>{
        var allNotes=ArrayList<String>()
        val c:Cursor=SQLiteDatabase.query("Notes",null,null,null,null,null,null)
        while (c.moveToNext()){
            allNotes.add(c.getString(c.getColumnIndex("Note")))
        }
        return allNotes
    }


    fun updateNote(input:String,n:String){
        val cv=ContentValues()
        cv.put("Note",input)
        SQLiteDatabase.update("Notes",cv,"Note=?", arrayOf(n))
    }

    fun deleteNote(n:String){
        SQLiteDatabase.delete("Notes","Note=?", arrayOf(n))
    }
}