package com.example.roomdb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.roomdb.adapters.UserAdapter
import com.example.roomdb.storage.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //whenever the activity is started, it reads data from database and stores it into
        // local array list 'items'
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "production")
                .build()

        //it is very bad practice to pull data from Room on main UI thread,
        // that's why we create another thread which we use for getting the data and displaying it
        val r = Runnable {
            val items = db.databaseInterface().allItems
            val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
            recyclerView.setLayoutManager(LinearLayoutManager(application))
            val adapter = UserAdapter(items)
            adapter.notifyDataSetChanged()
            recyclerView.setAdapter(adapter)
        }

        val newThread = Thread(r)
        newThread.start()

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener {
            val i = Intent(this@MainActivity, AddItemActivity::class.java)
            startActivity(i)
            finish()

        }


    }
}
