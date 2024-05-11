package com.example.mynotemvvm.Model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao    // Dao stands for Data access object it must be an Interface as Room db according to mvvm can define function on its own so only declaration is needed.
public interface NoteDao {
    @Insert
    public void insert(Note note); // @Insert is annotation used so that insert method can be converted without writing code.
    @Update
    public void update(Note note); //  @Update is annotation used so that update method can be converted without writing code.
    @Delete
    public void delete(Note note); //  @Delete is annotation used so that delete method can be converted without writing code.

    @Query("SELECT * FROM My_Notes") // No annotation for get all data so by using querry annotation it is done.
    public LiveData<List<Note>> getAllData(); //Livedata is type of linked list it is faster and it comes with Room db dependency
}
