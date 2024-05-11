package com.example.mynotemvvm.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;// automatic setting value of id by defining id as @Primary key

@Entity(tableName = "My_Notes") // by default table name created by Room db was "Note" but by using @Entity we have changed the db name to "My_Notes".
public class Note {

    private String title , disp;
    @PrimaryKey(autoGenerate = true)  //@primarykey(used by room db) is used by mvvm -code has been written by mvvm(@-annotation).
    private int id;

    public Note(String title, String disp) {
        this.title = title;
        this.disp = disp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisp() {
        return disp;
    }

    public void setDisp(String disp) {
        this.disp = disp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
