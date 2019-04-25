package com.example.roomdb.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomdb.objects.TodoListItem;

import java.util.List;

@Dao
public interface DatabaseInterface {

    @Query("SELECT * FROM todolistitem")
    List<TodoListItem> getAllItems();

    @Insert
    void insertAll(TodoListItem... todoListItems);

}


