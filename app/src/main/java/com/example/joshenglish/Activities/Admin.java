package com.example.joshenglish.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.joshenglish.Fragments.DeleteOps;
import com.example.joshenglish.Fragments.InsertOps;
import com.example.joshenglish.R;
import com.example.joshenglish.databinding.ActivityAdminBinding;

public class Admin extends AppCompatActivity {
    ActivityAdminBinding bnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        replaceFrag(new InsertOps());
        bnd.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(new InsertOps());
            }
        });
        bnd.delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                replaceFrag(new DeleteOps());
            }
        });
        bnd.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFrag(new DeleteOps());
            }
        });

    }
    public void replaceFrag(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.replFrag,fragment);
        transaction.commit();
    }
}