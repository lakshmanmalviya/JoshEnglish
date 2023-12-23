package com.example.joshenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.joshenglish.R;
import com.example.joshenglish.databinding.ActivityInsertSpeakingBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class InsertSpeaking extends AppCompatActivity {
     ActivityInsertSpeakingBinding bnd;
    FirebaseDatabase database;
    DatabaseReference myref;
    String allLesson = "allLesson";
    String lessonId ="lessonId";
    String topicName ="topicName";
    String rating ="rating";
    String speakId ="speakId";
    String Speaking ="Speaking";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertSpeakingBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        lessonId = getIntent().getStringExtra(lessonId);
        database = FirebaseDatabase.getInstance();
        bnd.insertSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.waitSpeak.setVisibility(View.VISIBLE);
                if (bnd.topicName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The something is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(allLesson).child(lessonId).child(Speaking).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(topicName,bnd.topicName.getText().toString());
                    map.put(speakId,myref.getKey());
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            bnd.waitSpeak.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "The Speaking topic is inserted", Toast.LENGTH_SHORT).show();
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