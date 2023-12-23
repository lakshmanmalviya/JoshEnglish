package com.example.joshenglish.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joshenglish.Modals.SpeakingModal;
import com.example.joshenglish.Modals.VocabModal;
import com.example.joshenglish.R;

import java.util.ArrayList;

public class SpeakAdapter extends RecyclerView.Adapter<SpeakAdapter.SpeakHolder> {
  ArrayList<SpeakingModal>slist;
  Context context;

    public SpeakAdapter(ArrayList<SpeakingModal> slist, Context context) {
        this.slist = slist;
        this.context = context;
    }

    @NonNull
    @Override
    public SpeakHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.speak_row,parent,false);
        return  new SpeakHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakHolder holder, int position) {
        SpeakingModal rmodal = slist.get(position);
        holder.topicName.setText(rmodal.getTopicName());
        holder.rating.setText(rmodal.getRating());
        holder.speakLaout.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.animone));
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }

    class SpeakHolder extends RecyclerView.ViewHolder{
        TextView topicName,rating;
        LinearLayout speakLaout;
        public SpeakHolder(@NonNull View itemView) {
            super(itemView);
            topicName= itemView.findViewById(R.id.rowTopicName);
            rating= itemView.findViewById(R.id.rowRating);
            speakLaout= itemView.findViewById(R.id.speakLaout);
        }
    }
}
