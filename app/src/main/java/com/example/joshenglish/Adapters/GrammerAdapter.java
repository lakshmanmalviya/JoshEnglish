package com.example.joshenglish.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.joshenglish.Modals.GrammerModal;
import com.example.joshenglish.Modals.SpeakingModal;
import com.example.joshenglish.R;

import java.util.ArrayList;

public class GrammerAdapter extends RecyclerView.Adapter<GrammerAdapter.GrammerHolder> {
    boolean isClick = false;
    MediaController mediaController;
   ArrayList<GrammerModal> glist;
   Context context;
    public GrammerAdapter(ArrayList<GrammerModal> glist, Context context) {
        this.glist = glist;
        this.context = context;
    }

    @NonNull
    @Override
    public GrammerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grammer_row,parent,false);
        mediaController = new MediaController(context);
        return  new GrammerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GrammerHolder holder, int position) {
        GrammerModal rmodal = glist.get(position);
        holder.videoName.setText(rmodal.getVideoName());
        holder.videoNotes.setText(rmodal.getVideoNotes());
        holder.video.setMediaController(mediaController);
        holder.video.start();
        holder.video.setVideoURI((Uri.parse(rmodal.getVideo())));
        holder.showVideoNoteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClick==false){
                    isClick =  true;
                holder.videoDown.setText("-");
                    holder.rowVideoNoteLayout.setVisibility(View.VISIBLE);
                }
                else{
                    isClick=false;
                    holder.videoDown.setText("+");
                    holder.rowVideoNoteLayout.setVisibility(View.GONE);
                }
            }
        });
        holder.grammerLayout.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.animone));
    }

    @Override
    public int getItemCount() {
        return glist.size();
    }

    class GrammerHolder extends RecyclerView.ViewHolder{
        TextView videoName,videoNotes,videoDown;
        VideoView video;
        LinearLayout showVideoNoteLayout,rowVideoNoteLayout,grammerLayout;
        public GrammerHolder(@NonNull View itemView) {
            super(itemView);
            videoName = itemView.findViewById(R.id.rowVideoName);
            videoDown = itemView.findViewById(R.id.videoDown);
            videoNotes = itemView.findViewById(R.id.videoNotes);
            video = itemView.findViewById(R.id.rowVideo);
            rowVideoNoteLayout = itemView.findViewById(R.id.rowVideoNoteLayout);
            showVideoNoteLayout = itemView.findViewById(R.id.showVideoNoteLayout);
            grammerLayout = itemView.findViewById(R.id.grammerLayout);
        }
    }
}
