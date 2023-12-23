package com.example.joshenglish.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.joshenglish.Adapters.LessonAdapter;
import com.example.joshenglish.Classes.RecyclerItemClickListener;
import com.example.joshenglish.Modals.LessonMadal;
import com.example.joshenglish.databinding.FragmentDeleteOpsBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteOps extends Fragment {
    FragmentDeleteOpsBinding bnd;
    LessonAdapter adapter;
    String allLesson = "allLesson";
    ArrayList<LessonMadal> llist;
    LinearLayoutManager layoutManager;
    FirebaseDatabase myDatabase;
    String lessonId = "lessonId";
    public DeleteOps() {  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        bnd = FragmentDeleteOpsBinding.inflate(inflater,container,false);
        myDatabase = FirebaseDatabase.getInstance();
        llist = new ArrayList<>();
        adapter = new LessonAdapter(llist,getContext());
        bnd.deleteOpsRec.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        bnd.deleteOpsRec.setLayoutManager(layoutManager);
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
                            Toast.makeText(getContext(), "Not a single Lesson  is there", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                });
        bnd.deleteOpsRec.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), bnd.deleteOpsRec, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
            Toast.makeText(getContext(), "press long to delete", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLongItemClick(View view, int position) {
                LessonMadal lmodal = llist.get(position);
                myDatabase.getReference().child(allLesson).child( lmodal.getLessonId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getContext(), "Deleted successfully", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Failed to delete", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));
        return bnd.getRoot();
    }
}