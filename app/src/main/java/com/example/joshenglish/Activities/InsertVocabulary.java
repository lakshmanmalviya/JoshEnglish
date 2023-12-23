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
import com.example.joshenglish.databinding.ActivityInsertVocabularyBinding;
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

public class InsertVocabulary extends AppCompatActivity {
     ActivityInsertVocabularyBinding bnd;
    String allLesson = "allLesson";
    FirebaseDatabase myDatabase;
    FirebaseStorage storage;
    String lessonId = "lessonId";
    String Vocabulary = "Vocabulary";
    DatabaseReference myref;
    String imageUrl ="";
    String vocabName ="vocabName";
    String vocabImage ="vocabImage";
    String vocabSentence ="vocabSentence";
    String vocabId ="vocabId";
    ActivityResultLauncher<String> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertVocabularyBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        lessonId = getIntent().getStringExtra(lessonId);
        myDatabase = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        bnd.waitVocab.setVisibility(View.GONE);
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                bnd.vocabImg.setImageURI(uri);
                bnd.waitVocab.setVisibility(View.VISIBLE);
                bnd.insertVocab.setVisibility(View.GONE);
                final StorageReference reference = storage.getReference().child(allLesson).child(allLesson+"67656allLesson"+"-"+new Date().getTime());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                imageUrl = uri.toString();
                                Toast.makeText(getApplicationContext(), ""+imageUrl, Toast.LENGTH_SHORT).show();
                                bnd.waitVocab.setVisibility(View.GONE);
                                bnd.insertVocab.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                });
            }
        });
        bnd.brVocabImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.insertVocab.setVisibility(View.GONE);
                launcher.launch("image/*");
            }
        });
        bnd.insertVocab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnd.vocabName.getText().toString().isEmpty()||bnd.vocabSentences.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The something is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = myDatabase.getReference().child(allLesson).child(lessonId).child(Vocabulary).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(vocabName,bnd.vocabName.getText().toString());
                    map.put(vocabImage,imageUrl);
                    map.put(vocabSentence,bnd.vocabSentences.getText().toString());
                    map.put(lessonId,myref.getKey());
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "The vocab is inserted", Toast.LENGTH_SHORT).show();
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