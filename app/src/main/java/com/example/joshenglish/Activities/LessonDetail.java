package com.example.joshenglish.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.joshenglish.Adapters.FragAdapter;
import com.example.joshenglish.Fragments.Grammer;
import com.example.joshenglish.Fragments.Reading;
import com.example.joshenglish.Fragments.Speaking;
import com.example.joshenglish.Fragments.Vocabulary;
import com.example.joshenglish.Modals.VocabModal;
import com.example.joshenglish.databinding.ActivityLessonDetailBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LessonDetail extends AppCompatActivity {
    ActivityLessonDetailBinding bnd;
    static  String lessonId = "lessonId";
    static String nowLessonid = "";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        bnd = ActivityLessonDetailBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        lessonId = getIntent().getStringExtra(lessonId);
        updateId();
        callId();
            bnd.viewPager.setAdapter(new FragAdapter(getSupportFragmentManager()));
            bnd.tabLayout.setupWithViewPager(bnd.viewPager);
            bnd.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }
                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

    }
    public String lessonId(){
       lessonId = getIntent().getStringExtra(lessonId);
       return lessonId;
    }
    public static void callId() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference("lessonId").child("id");
        ValueEventListener id = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                nowLessonid = snapshot.getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        myref.addValueEventListener(id);
    }
    public  static  String giveId(){
       return nowLessonid;
    }
    public void updateId(){
       FirebaseDatabase.getInstance().getReference().child("lessonId").child("id").setValue(getIntent().getStringExtra("lessonId")).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void unused) {
           }
       }).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
           }
       });
    }
}