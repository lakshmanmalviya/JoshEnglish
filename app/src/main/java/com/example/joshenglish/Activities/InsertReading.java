package com.example.joshenglish.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.joshenglish.R;
import com.example.joshenglish.databinding.ActivityInsertLessonBinding;
import com.example.joshenglish.databinding.ActivityInsertReadingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.Map;

public class InsertReading extends AppCompatActivity {
    ActivityInsertReadingBinding bnd;
    FirebaseDatabase database;
    DatabaseReference myref;
    String allLesson = "allLesson";
    String lessonId ="lessonId";
    String readNote ="readNote";
    String storyName ="storyName";
    String storyContent ="storyContent";
    String storyId ="storyId";
    String Reading ="Reading";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertReadingBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        lessonId = getIntent().getStringExtra(lessonId);
        database = FirebaseDatabase.getInstance();
        bnd.insertReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.waitRead.setVisibility(View.VISIBLE);
                if (bnd.storyNote.getText().toString().isEmpty()||bnd.storyName.getText().toString().isEmpty()||bnd.storyContent.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The something is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(allLesson).child(lessonId).child(Reading).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(storyName,bnd.storyName.getText().toString());
                    map.put(storyContent,bnd.storyContent.getText().toString());
                    map.put(readNote,bnd.storyNote.getText().toString());
                    map.put(storyId,myref.getKey());
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            bnd.waitRead.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "The Story is inserted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed"+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }
}