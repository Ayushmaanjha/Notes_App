package com.example.mynotemvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotemvvm.Model.Note;
import com.example.mynotemvvm.Model.NoteRepo;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepo noteRepo;
    private LiveData<List<Note>> notelist;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        notelist = noteRepo.getAllData();
    }

    public void Insert(Note note){
        noteRepo.insertData(note);
    }
    public void Delete(Note note){
        noteRepo.deleteData(note);
    }
    public void Update(Note note){
        noteRepo.updateData(note);
    }

    public LiveData<List<Note>> getNotelist(){
        return notelist;
    }
}
