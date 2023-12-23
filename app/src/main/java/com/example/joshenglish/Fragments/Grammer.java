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
import com.example.joshenglish.Adapters.GrammerAdapter;
import com.example.joshenglish.Modals.GrammerModal;
import com.example.joshenglish.databinding.FragmentGrammerBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Grammer extends Fragment {
  FragmentGrammerBinding bnd;
    GrammerAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<GrammerModal> glist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    String Grammer = "Grammer";
    public Grammer() {  }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bnd = FragmentGrammerBinding.inflate(inflater,container,false);
        bnd.GprogressBar.setVisibility(View.VISIBLE);
        myDatabase = FirebaseDatabase.getInstance();
        lessonId = LessonDetail.giveId();
        if (lessonId!=null) {
        glist = new ArrayList<>();
        adapter = new GrammerAdapter(glist,getContext());
        bnd.grammerRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bnd.grammerRec.setLayoutManager(layoutManager);
        myDatabase.getReference().child(allLesson).child(lessonId).child(Grammer)
                .addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            glist.clear();
                            for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                                GrammerModal rmodal = dataSnapshot.getValue(GrammerModal.class);
                                glist.add(rmodal);
                            }
                            adapter.notifyDataSetChanged();
                            bnd.GprogressBar.setVisibility(View.GONE);
                        }
                        if (!snapshot.exists()){
                            Toast.makeText(getContext(), "No content exists", Toast.LENGTH_SHORT).show();
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