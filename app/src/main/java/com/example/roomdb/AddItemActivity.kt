package com.example.roomdb

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.roomdb.objects.TodoListItem
import com.example.roomdb.storage.AppDatabase

class AddItemActivity : AppCompatActivity() {

    internal lateinit var todo: EditText
    internal lateinit var time: EditText
    internal lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)


        todo = findViewById(R.id.name) as EditText
        time = findViewById(R.id.time) as EditText
        button = findViewById(R.id.button) as Button


        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "production")
                .build()

        button.setOnClickListener {

            if (todo.text.toString() != "" && time.text.toString() != "") {

                val todoListItem = TodoListItem(todo.text.toString(), time.text.toString())
                //save the item before leaving the activity


                AsyncTask.execute { db.databaseInterface().insertAll(todoListItem) }

                Toast.makeText(baseContext, todoListItem.time, Toast.LENGTH_SHORT).show()

                val i = Intent(this@AddItemActivity, MainActivity::class.java)
                startActivity(i)

                finish()

            }
        }
    }
}
