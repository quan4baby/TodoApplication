package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter(
        // declare a MutableList of ToDos
        var todos: MutableList<ToDo>
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    // declare a ViewHolder that will hold the Layout of an item in
    // the RecyclerView
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // declare TextView mutable field using null safety
    var todoTextView: TextView? = null

    // declare CheckBox mutable field using null safety
    var todoCheckBox: CheckBox? = null

    /**
     * Create a ToDoViewHolder that reference the li_main layout resource
     * and return it.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.li_main,
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        // get current item in MutableList of ToDos and store it in
         // immutable variable
        val currentToDo = todos[position]

        holder.itemView.apply {
            // make TextView refer to TextView in li_main layout resource
            todoTextView = findViewById<View>(R.id.todoTextView) as TextView
            // tell Kotlin that TextView isn't null
            // assign the name value in the current item to text attribute of
            // TextView
            todoTextView!!.text = currentToDo.name

            // make CheckBox refer to the CheckBox in li_main resource
            todoCheckBox = findViewById<View>(R.id.todoCheckBox) as CheckBox
            // tell Kotlin that CheckBox isn't null
            // assign the isChecked value in the current item to isChecked
            // atttribute of CheckBox
            todoCheckBox!!.isChecked = currentToDo.isChecked
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }


}