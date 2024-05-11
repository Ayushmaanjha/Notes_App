package com.example.mynotemvvm.Model;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Note.class,version = 1)
public abstract class NoteDB extends RoomDatabase {

    private static NoteDB instance;
    public abstract NoteDao noteDao();
    public static synchronized NoteDB getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), // if DB was not created then this method should run and create DB.
                    NoteDB.class,"note_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
