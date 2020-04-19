package com.example.infs3605projecttest4;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.activity.PairsActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PairsAdapter extends RecyclerView.Adapter<PairsAdapter.PairsViewHolder> {
    private PairsActivity currActivity;
    private ArrayList<String> data;

    public void setCurrActivity (PairsActivity activity) {
        currActivity = activity;
    }

    public void setData (ArrayList<String> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PairsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sentence_holder,parent
                ,false);
        return new PairsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PairsViewHolder holder, int position) {
        holder.word.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class PairsViewHolder extends RecyclerView.ViewHolder{

        TextView word;

        public PairsViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.se_holder_word);
            word.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
