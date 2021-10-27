package com.example.notesappsaveonly

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.*
import kotlinx.android.synthetic.main.item_row.view.*

class myAdapter(private val notes:ArrayList<String>,val activity: MainActivity): RecyclerView.Adapter<myAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView : View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name=notes[position]
        holder.itemView.apply {
            textView.text= name

            imageButtonEdit.setOnClickListener {
              dailog(name,activity)
                activity.recyclerView() // this line does not work you have to rotate the device to update the recyclerview
            }

            imageButtonDelete.setOnClickListener {
                val dbHelper=DbHelper(activity)
                dbHelper.deleteNote(name)
                activity.recyclerView()
            }
        }

    }

    override fun getItemCount()=notes.size
}
fun dailog(n: String,activity: MainActivity){
    val dbHelper=DbHelper(activity)
    val builder = AlertDialog.Builder(activity)
    val input= EditText(activity)
    input.setText(n)
    builder.setPositiveButton("Save", DialogInterface.OnClickListener{
            dialog, id -> dbHelper.updateNote(input.text.toString(),n)
    })
    builder.setNegativeButton("Cancel", DialogInterface.OnClickListener(){
            dialog, id -> dialog.cancel()
    })

    val alert = builder.create()
    alert.setTitle("Update Note")
    alert.setView(input)
    alert.show()

}


