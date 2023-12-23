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

import com.example.joshenglish.Modals.ReadingModal;
import com.example.joshenglish.Modals.VocabModal;
import com.example.joshenglish.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VocabAdapter  extends RecyclerView.Adapter<VocabAdapter.vocabHolder>{
    ArrayList<VocabModal> vlist;
    Context context;
    boolean isClick=false;

    public VocabAdapter(ArrayList<VocabModal> vlist, Context context) {
        this.vlist = vlist;
        this.context = context;
    }

    @NonNull
    @Override
    public vocabHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.vocab_row,parent,false);
        return  new vocabHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull vocabHolder holder, int position) {
        VocabModal rmodal = vlist.get(position);
        holder.vocabName.setText(rmodal.getVocabName());
        holder.vocabSentence.setText(rmodal.getVocabSentence());
        holder.vocabLayout.setAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(),R.anim.animone));
       Picasso.get().load(rmodal.getVocabImage()).placeholder(R.drawable.placeholder).into(holder.vocabImage);

        holder.vocabTouchLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isClick==false){
                    holder.vocabImgSenLayout.setVisibility(View.VISIBLE);
                    holder.plus.setText("-");
                    isClick = true;
                }
                else {
                    holder.vocabImgSenLayout.setVisibility(View.GONE);
                    holder.plus.setText("+");
                    isClick = false;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return vlist.size();
    }

    class vocabHolder extends RecyclerView.ViewHolder{
         TextView vocabName,vocabSentence,plus;
         ImageView vocabImage;
         LinearLayout vocabTouchLayout,vocabImgSenLayout,vocabLayout;

        public vocabHolder(@NonNull View itemView) {
            super(itemView);

            vocabName = itemView.findViewById(R.id.rowVocabName);
            vocabImage = itemView.findViewById(R.id.rowVocabImg);
            plus = itemView.findViewById(R.id.plus);
            vocabSentence = itemView.findViewById(R.id.rowVocabSentence);
            vocabTouchLayout = itemView.findViewById(R.id.vocabTouchLayout);
            vocabImgSenLayout = itemView.findViewById(R.id.vocabImgSenLayout);
            vocabLayout = itemView.findViewById(R.id.vocabLayout);
        }
    }
}
