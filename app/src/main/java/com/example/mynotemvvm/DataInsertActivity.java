package com.example.mynotemvvm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mynotemvvm.databinding.ActivityDataInsertBinding;

import javax.xml.transform.Result;

public class DataInsertActivity extends AppCompatActivity {
ActivityDataInsertBinding binding; // Viewbinding is used in place of findViewby.id to write the code in simple way and
                                   // it get inflate with the layout of the activity automatically and it can acces those who have theie id's.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        binding = ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String type = getIntent().getStringExtra("type");
        if (type.equals("update")){
            binding.editTextTitle.setText(getIntent().getStringExtra("title"));
            binding.editTextDisp.setText(getIntent().getStringExtra("disp"));
            int id = getIntent().getIntExtra("id",0);
            binding.buttonAdd.setText("update");

            binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.editTextTitle.getText().toString();
                    String disp = binding.editTextDisp.getText().toString();
                    if(!title.isEmpty()&&!disp.isEmpty()){
                        Intent it = new Intent();
                        it.putExtra("title",title);
                        it.putExtra("disp",disp);
                        it.putExtra("id",id);
                        setResult(RESULT_OK,it);
                        finish();
                    }else{
                        Toast.makeText(DataInsertActivity.this, "Fill the details", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            setTitle("Add Note");
            binding.buttonAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String title = binding.editTextTitle.getText().toString();
                    String disp = binding.editTextDisp.getText().toString();
                    if(!title.isEmpty()&&!disp.isEmpty()){
                        Intent it = new Intent();
                        it.putExtra("title",title);
                        it.putExtra("disp",disp);
                        setResult(RESULT_OK,it);
                        finish();
                    }else{
                        Toast.makeText(DataInsertActivity.this, "Fill the details", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class));
    }
}