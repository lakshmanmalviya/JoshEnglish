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
import com.example.joshenglish.Adapters.SpeakAdapter;
import com.example.joshenglish.Modals.SpeakingModal;
import com.example.joshenglish.databinding.FragmentSpeakingBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Speaking extends Fragment {
    FragmentSpeakingBinding bnd;
    SpeakAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<SpeakingModal> sllist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    String Speaking = "Speaking";
    public Speaking() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bnd = FragmentSpeakingBinding.inflate(inflater,container,false);
        bnd.SprogressBar.setVisibility(View.VISIBLE);
        myDatabase = FirebaseDatabase.getInstance();
        lessonId = LessonDetail.giveId();
        if (lessonId!=null) {
            sllist = new ArrayList<>();
            adapter = new SpeakAdapter(sllist, getContext());
            bnd.speakRec.setAdapter(adapter);
            layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
            bnd.speakRec.setLayoutManager(layoutManager);
            myDatabase.getReference().child(allLesson).child(lessonId).child(Speaking)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                sllist.clear();
                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                    SpeakingModal rmodal = dataSnapshot.getValue(SpeakingModal.class);
                                    sllist.add(rmodal);
                                }
                                adapter.notifyDataSetChanged();
                                bnd.SprogressBar.setVisibility(View.GONE);

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), "" + error, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return bnd.getRoot();
    }
}