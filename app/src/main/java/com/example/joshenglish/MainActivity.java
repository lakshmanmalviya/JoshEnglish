package com.example.joshenglish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.joshenglish.Activities.Admin;
import com.example.joshenglish.Activities.LessonDetail;
import com.example.joshenglish.Adapters.LessonAdapter;
import com.example.joshenglish.Classes.RecyclerItemClickListener;
import com.example.joshenglish.Modals.LessonMadal;
import com.example.joshenglish.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int changeMode =0;
   ActivityMainBinding bnd;
   LessonAdapter adapter;
    String allLesson = "allLesson";
   ArrayList<LessonMadal> llist;
   LinearLayoutManager layoutManager;
   FirebaseDatabase myDatabase;
   String lessonId = "lessonId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        myDatabase = FirebaseDatabase.getInstance();
        llist = new ArrayList<>();
        adapter = new LessonAdapter(llist,getApplicationContext());
        bnd.lessonRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        bnd.lessonRec.setLayoutManager(layoutManager);
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
        bnd.lessonRec.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), bnd.lessonRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LessonMadal lmodal = llist.get(position);
                Intent intent = new Intent(getApplicationContext(), LessonDetail.class);
                intent.putExtra(lessonId,lmodal.getLessonId());
//          Toast.makeText(getApplicationContext(), cmodal.getCategoryName()+" clicked "+position, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));

        bnd.temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admin.class));
            }
        });
    }
}