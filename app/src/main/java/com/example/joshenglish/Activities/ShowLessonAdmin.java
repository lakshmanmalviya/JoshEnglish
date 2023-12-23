package com.example.joshenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.joshenglish.Adapters.LessonAdapter;
import com.example.joshenglish.Classes.RecyclerItemClickListener;
import com.example.joshenglish.Modals.LessonMadal;
import com.example.joshenglish.R;
import com.example.joshenglish.databinding.ActivityMainBinding;
import com.example.joshenglish.databinding.ActivityShowLessonAdminBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowLessonAdmin extends AppCompatActivity {
    ActivityShowLessonAdminBinding bnd;
    LessonAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<LessonMadal> llist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    String argsName = "argsName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityShowLessonAdminBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        argsName = getIntent().getStringExtra(argsName);
        myDatabase = FirebaseDatabase.getInstance();
        llist = new ArrayList<>();
        adapter = new LessonAdapter(llist,getApplicationContext());
        bnd.showLessonRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        bnd.showLessonRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allLesson)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            llist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                LessonMadal lmodal = dataSnapshot.getValue(LessonMadal.class);
                                llist.add(lmodal);
                            }
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Not a single Lesson  is there", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getApplicationContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        bnd.showLessonRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), bnd.showLessonRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LessonMadal lmodal = llist.get(position);
                if(argsName.equalsIgnoreCase("Grammer")){
                    Intent intent = new Intent(getApplicationContext(), InsertGrammer.class);
                    intent.putExtra(lessonId,lmodal.getLessonId());
                    startActivity(intent);
                }
                else if(argsName.equalsIgnoreCase("Vocabulary")){
                    Intent intent = new Intent(getApplicationContext(), InsertVocabulary.class);
                    intent.putExtra(lessonId,lmodal.getLessonId());
                    startActivity(intent);
                }
                else if(argsName.equalsIgnoreCase("Reading")){
                    Intent intent = new Intent(getApplicationContext(), InsertReading.class);
                    intent.putExtra(lessonId,lmodal.getLessonId());
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), InsertSpeaking.class);
                    intent.putExtra(lessonId,lmodal.getLessonId());
                    startActivity(intent);
                }

          Toast.makeText(getApplicationContext(), " clicked "+argsName, Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }
}