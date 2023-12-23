package com.example.joshenglish.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.joshenglish.Activities.InsertGrammer;
import com.example.joshenglish.Activities.InsertLesson;
import com.example.joshenglish.Activities.InsertReading;
import com.example.joshenglish.Activities.InsertSpeaking;
import com.example.joshenglish.Activities.InsertVocabulary;
import com.example.joshenglish.Activities.ShowLessonAdmin;
import com.example.joshenglish.databinding.FragmentInsertOpsBinding;

public class InsertOps extends Fragment {
   FragmentInsertOpsBinding bnd;
   String argsName = "argsName";
    public InsertOps() {  }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bnd = FragmentInsertOpsBinding.inflate(inflater,container,false);
        bnd.addLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), InsertLesson.class));
            }
        });
        bnd.addGrammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowLessonAdmin.class);
                intent.putExtra(argsName,"Grammer");
                startActivity(intent);

            }
        });
        bnd.addReading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowLessonAdmin.class);
                intent.putExtra(argsName,"Reading");
                startActivity(intent);

            }
        });
        bnd.addSpeaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowLessonAdmin.class);
                intent.putExtra(argsName,"Speaking");
                startActivity(intent);
            }
        });
        bnd.addVocabulary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShowLessonAdmin.class);
                intent.putExtra(argsName,"Vocabulary");
                startActivity(intent);
            }
        });
        return bnd.getRoot();
    }
}