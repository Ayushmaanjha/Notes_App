package com.example.mynotemvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynotemvvm.Model.Note;
import com.example.mynotemvvm.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(NoteViewModel.class);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this,DataInsertActivity.class);
                it.putExtra("type","Add Note");
                startActivityForResult(it,1);
            }
        });
        binding.Rv.setLayoutManager(new LinearLayoutManager(this));
        binding.Rv.setHasFixedSize(true);
        RVAdapter adapter = new RVAdapter();
        binding.Rv.setAdapter(adapter);

        noteViewModel.getNotelist().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction==ItemTouchHelper.RIGHT){
                    noteViewModel.Delete(adapter.GetNote(viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "data deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Intent it= new Intent(MainActivity.this, DataInsertActivity.class);
                    it.putExtra("type","update");
                    it.putExtra("title",adapter.GetNote(viewHolder.getAdapterPosition()).getTitle());
                    it.putExtra("disp",adapter.GetNote(viewHolder.getAdapterPosition()).getDisp());
                    it.putExtra("id",adapter.GetNote(viewHolder.getAdapterPosition()).getId());
                    startActivityForResult(it,2);
                }

            }
        }).attachToRecyclerView(binding.Rv);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1){
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
                Note note = new Note(title,disp);
                noteViewModel.Insert(note);
                Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();

        }
        else if(requestCode==2){
            String title = data.getStringExtra("title");
            String disp = data.getStringExtra("disp");
            Note note = new Note(title,disp);
            note.setId(data.getIntExtra("id",0));
            noteViewModel.Update(note);
            Toast.makeText(this, "Note Updated", Toast.LENGTH_SHORT).show();
        }
    }
}