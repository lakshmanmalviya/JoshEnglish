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

import com.example.joshenglish.Modals.ReadingModal;
import com.example.joshenglish.R;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ReadHolder> {
  ArrayList<ReadingModal> rlist;
  Context context;

    public ReadingAdapter(ArrayList<ReadingModal> rlist, Context context) {
        this.rlist = rlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ReadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reading_row,parent,false);
        return  new ReadHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadHolder holder, int position) {
        ReadingModal rmodal = rlist.get(position);
        holder.readNote.setText(rmodal.getReadNote());
        holder.storyName.setText(rmodal.getStoryName());
        holder.storyContent.setText(rmodal.getStoryContent());
        holder.layout.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.animone));
    }

    @Override
    public int getItemCount() {
        return rlist.size();
    }

    class ReadHolder extends RecyclerView.ViewHolder{
        TextView readNote,storyName,storyContent;
        LinearLayout layout;
        public ReadHolder(@NonNull View itemView) {
            super(itemView);
            readNote = itemView.findViewById(R.id.rowReadNote);
            storyName = itemView.findViewById(R.id.rowStoryName);
            storyContent = itemView.findViewById(R.id.rowStoryContent);
            layout = itemView.findViewById(R.id.readLayouot);
        }
    }
}
