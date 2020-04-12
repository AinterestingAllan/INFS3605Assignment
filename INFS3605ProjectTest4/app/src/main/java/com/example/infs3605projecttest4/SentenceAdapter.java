package com.example.infs3605projecttest4;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.activity.SentenceActivity;

import java.util.ArrayList;

public class SentenceAdapter extends RecyclerView.Adapter<SentenceAdapter.MyViewHolder> {
    // not selected word ay
    public static ArrayList<Word> notSelectedWordAy = null;
    public static ArrayList<Word> selectWordAy = new ArrayList<>();
    private SentenceActivity currActivity = null;
    private String TAG = "";


    public void setCurrActivity(SentenceActivity currActivity) {
        this.currActivity = currActivity;
    }

    public SentenceAdapter (String TAG) {
        this.TAG = TAG;
    }

    public static ArrayList<Word> getSelectWordAy() {
        return selectWordAy;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sentence_holder,parent
                ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(TAG.equals("notSelected")) {
            holder.se_holder_word.setText(notSelectedWordAy.get(position).getLocal());
        } else {
            if(selectWordAy.size()!=0) {
                holder.se_holder_word.setText(selectWordAy.get(position).getLocal());
            }
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (TAG.equals("notSelected")) {
            size = notSelectedWordAy.size();
        } else {
            size = selectWordAy.size();
        }
        return size;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView se_holder_word;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            se_holder_word = itemView.findViewById(R.id.se_holder_word);
            se_holder_word.setOnClickListener(this);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onClick(View v){
            if(TAG.equals("notSelected")) {
                selectWordAy.add(notSelectedWordAy.remove(getAdapterPosition()));
                SentenceActivity.seAd1.notifyDataSetChanged();
                SentenceActivity.seAd2.notifyDataSetChanged();
            }else {
                notSelectedWordAy.add(selectWordAy.remove(getAdapterPosition()));
                SentenceActivity.seAd1.notifyDataSetChanged();
                SentenceActivity.seAd2.notifyDataSetChanged();
            }
        }
    }


}
