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
import com.example.joshenglish.Adapters.VocabAdapter;
import com.example.joshenglish.Modals.VocabModal;
import com.example.joshenglish.databinding.FragmentVocabularyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Vocabulary extends Fragment {
    FragmentVocabularyBinding bnd;
    VocabAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<VocabModal> rllist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    String Vocabulary = "Vocabulary";
    public Vocabulary() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bnd = FragmentVocabularyBinding.inflate(inflater,container,false);
        myDatabase = FirebaseDatabase.getInstance();
        lessonId  =LessonDetail.giveId();
        bnd.VprogressBar.setVisibility(View.VISIBLE);
        if (lessonId!=null) {
        rllist = new ArrayList<>();
        adapter = new VocabAdapter(rllist,getContext());
        bnd.vocabRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bnd.vocabRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allLesson).child(lessonId).child(Vocabulary)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            rllist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                VocabModal rmodal = dataSnapshot.getValue(VocabModal.class);
                                rllist.add(rmodal);
                            }
                            adapter.notifyDataSetChanged();
                            bnd.VprogressBar.setVisibility(View.GONE);
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