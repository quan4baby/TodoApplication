package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // declare EditText as mutable field using safety
    var todoEditText: EditText? = null

    // declare RecyclerView as mutable field using null safety
    var todoRecyclerView: RecyclerView? = null

    // declare DBHandler as mutable field using null safety
    var dbHandler: DBHandler? = null

    // declare a ToDoAdapter as a mutable field
    // specify that it will be initialized later
    lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // make EditText refer to actual EditText in activity_main layout resource
        todoEditText = findViewById<View>(R.id.todoEditText) as EditText

        // make RecyclerView refer to actual RecyclerView in activity_main layout resource
        todoRecyclerView = findViewById<View>(R.id.todoRecyclerView) as RecyclerView

        // fully initialize dbHandler
        dbHandler = DBHandler(this, null)

        // initialize the toDoAdapter
        toDoAdapter = ToDoAdapter(dbHandler!!.todos)

        // tell Kotlin that RecyclerView isn't null and set the toDoAdapter on it
        todoRecyclerView!!.adapter = toDoAdapter

        // tell Kotlin that the RecyclerView isn't null and apply a LinearLayout to it
        todoRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    /**
     * This function gets called when the Add Button is clicked
     * It adds todo into the database.
     * @param view MainActivity view
     */
    fun addTodo(view: View?){
        // tell Kotlin that EditText isn't null
        // get text input in EditText as String
        // store it in variable
        val todoName = todoEditText!!.text.toString()

        // trim variable and check if it's equal to an empty String
        if(todoName.trim() == ""){
            // display "Please enter a ToDo! Toast
            Toast.makeText(this, "Please enter a Todo!", Toast.LENGTH_LONG).show()
        }else {
            // ask Kotlin to check if the dbHandler is null
                // if it's not, apply all of the code in the let block to it
                    // in the let block, may refer to the dbHandler as it
            dbHandler?.let {
                    // call method in toDoAdapter that will add ToDo
                    // into the database
                    toDoAdapter.addToDo(it, todoName)
            }

            // display "Todo added!" Toast
            Toast.makeText(this, "Todo added!", Toast.LENGTH_LONG).show()

            // clear EditText
            todoEditText!!.text.clear()

            // call notifyAdapter method
            notifyAdapter()
        }
    }

    /**
     * This function gets called when the Delete Button is clicked
     * It deletes selected todos into the database.
     * @param view MainActivity view
     */
    fun deleteTodo(view: View?){

        // ask Kotlin to check if the dbHandler is null
        // if it isn't null, pass it to the deleteToDos method in
        // the toDoAdapter
        dbHandler?.let {
            toDoAdapter.deleteTodos(it)
        }

        // call notifyAdapter method
        notifyAdapter()
    }

    /**
     * This method updates the MutabeLists of ToDos in the toDoAdapter
     * with the most current data in the database.
     */
    private fun notifyAdapter(){
        // tell kotlin that dbHandler isn't null
        // call its getter method that returns the data in the
        // database as a MutableList
        // assign Mutable list to toDoAdapter's MutableList of ToDos
        toDoAdapter.todos = dbHandler!!.todos
    }
}