package com.example.joshenglish.Activities;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.joshenglish.databinding.ActivityInsertGrammerBinding;
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

public class InsertGrammer extends AppCompatActivity {
    ActivityInsertGrammerBinding bnd;
    ActivityResultLauncher<String> launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference myref;
    String allLesson = "allLesson";
    String lessonId ="lessonId";
    String Grammer ="Grammer";
    String video = "video";
    String videoName = "videoName";
    String videoNotes = "videoNotes";
    String grammerId = "grammerId";
    String videoUrl = "";
    MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bnd = ActivityInsertGrammerBinding.inflate(getLayoutInflater());
        setContentView(bnd.getRoot());
        getSupportActionBar().hide();
        lessonId = getIntent().getStringExtra(lessonId);
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        mediaController = new MediaController(getApplicationContext());
        bnd.video.setMediaController(mediaController);
        bnd.video.start();
        launcher = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri uri) {
                bnd.video.setVideoURI(uri);
                bnd.waitGram.setVisibility(View.VISIBLE);
                bnd.insertGrammer.setVisibility(View.GONE);
                final StorageReference reference = storage.getReference().child(allLesson).child(allLesson+"lessons"+"-"+new Date().getTime());
                reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                videoUrl = uri.toString();
                                Toast.makeText(getApplicationContext(), ""+ videoUrl, Toast.LENGTH_SHORT).show();
                                bnd.waitGram.setVisibility(View.GONE);
                                bnd.insertGrammer.setVisibility(View.VISIBLE);
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

        bnd.brVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bnd.insertGrammer.setVisibility(View.GONE);
                launcher.launch("video/*");
            }
        });
        bnd.insertGrammer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bnd.videoName.getText().toString().isEmpty()||bnd.videoInsNotes.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "The some area is empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    myref = database.getReference().child(allLesson).child(lessonId).child(Grammer).push();
                    Map<String,Object> map = new HashMap<>();
                    map.put(videoName,bnd.videoName.getText().toString());
                    map.put(videoNotes,bnd.videoInsNotes.getText().toString());
                    map.put(video, videoUrl);
                    map.put(grammerId,myref.getKey());
                    myref.setValue(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "The grammer is inserted", Toast.LENGTH_SHORT).show();
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