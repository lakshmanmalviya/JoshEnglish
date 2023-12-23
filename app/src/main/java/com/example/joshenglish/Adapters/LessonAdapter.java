package com.example.joshenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joshenglish.Modals.LessonMadal;
import com.example.joshenglish.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.lessonHolder> {
     ArrayList<LessonMadal> llist;
     Context context;

    public LessonAdapter(ArrayList<LessonMadal> llist, Context context) {
        this.llist = llist;
        this.context = context;
    }

    @NonNull
    @Override
    public lessonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.lesson_row,parent,false);
        return  new lessonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lessonHolder holder, int position) {
        LessonMadal lModal = llist.get(position);
        if (lModal.getLessonImg().isEmpty()){
            lModal.setLessonImg("https://firebasestorage.googleapis.com/v0/b/musicmg-9d5bf.appspot.com/o/bollywoodSong%2FbollywoodSong-41673627561042?alt=media&token=d834c668-ed92-45bf-9302-aeb114c196ed");
        }
        Picasso.get().load(lModal.getLessonImg()).placeholder(R.drawable.placeholder).into(holder.lessonImg);
        holder.lessonName.setText(lModal.getLessonName());
        holder.layout.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.animone));
    }

    @Override
    public int getItemCount() {
        return llist.size();
    }

    class lessonHolder extends RecyclerView.ViewHolder{
        ImageView lessonImg;
        TextView lessonName;
        LinearLayout  layout;
        public lessonHolder(@NonNull View itemView) {
            super(itemView);
            lessonImg = itemView.findViewById(R.id.rowLessonImg);
            lessonName = itemView.findViewById(R.id.rowLessonName);
            layout = itemView.findViewById(R.id.lessRowLayout);
    }
}
}
