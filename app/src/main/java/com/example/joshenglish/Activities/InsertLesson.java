package com.example.joshenglish.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.joshenglish.R;
import com.example.joshenglish.databinding.ActivityInsertLessonBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InsertLesson extends AppCompatActivity {
    ActivityInsertLessonBinding bnd;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference myref;
    String allLesson = "allLesson";
    String lessonImg ="lessonImg";
    String lessonName ="lessonName";
    String lessonId ="lessonId";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertLessonBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                bnd.lessonImg.setImageURI(uri);
                bnd.waitCat.setVisibility(View.VISIBLE);
                bnd.insertLesson.setVisibility(View.GONE);
                final StorageReference reference = storage.getReference().child(allLesson).child(allLesson+"lessons"+"-"+new Date().getTime());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageUrl = uri.toString();
                                Toast.makeText(getApplicationContext(), ""+imageUrl, Toast.LENGTH_SHORT).show();
                                bnd.waitCat.setVisibility(View.GONE);
                                bnd.insertLesson.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error while inserting the data "+e, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        bnd.brLessonImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.insertLesson.setVisibility(View.GONE);
                launcher.launch("image/*");
            }
        });
        bnd.insertLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnd.lessonName.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The Lesson Name is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(allLesson).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(lessonName,bnd.lessonName.getText().toString());
                    map.put(lessonImg,imageUrl);
                    map.put(lessonId,myref.getKey());
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "The lesson is inserted", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Failed"+e, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}