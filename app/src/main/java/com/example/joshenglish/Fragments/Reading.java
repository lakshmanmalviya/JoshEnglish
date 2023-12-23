package com.example.joshenglish.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.joshenglish.Activities.LessonDetail;
import com.example.joshenglish.Adapters.ReadingAdapter;
import com.example.joshenglish.Modals.ReadingModal;
import com.example.joshenglish.databinding.FragmentReadingBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Reading extends Fragment {
    FragmentReadingBinding bnd;
    ReadingAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<ReadingModal> rllist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    String Reading = "Reading";
    public Reading() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bnd = FragmentReadingBinding.inflate(inflater,container,false);
        bnd.RprogressBar.setVisibility(View.VISIBLE);

        myDatabase = FirebaseDatabase.getInstance();
        lessonId = LessonDetail.giveId();
//        Toast.makeText(getContext(), ""+lessonId, Toast.LENGTH_SHORT).show();
        if (lessonId!=null) {
        rllist = new ArrayList<>();
        adapter = new ReadingAdapter(rllist,getContext());
        bnd.readingRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bnd.readingRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allLesson).child(lessonId).child(Reading)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            rllist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                ReadingModal rmodal = dataSnapshot.getValue(ReadingModal.class);
                                rllist.add(rmodal);
                            }
                            adapter.notifyDataSetChanged();
                            bnd.RprogressBar.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        }
        return bnd.getRoot();
    }
}