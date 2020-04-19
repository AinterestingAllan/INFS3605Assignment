package com.example.infs3605projecttest4.ui.guide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.Model.Alphabet;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.database.Warehouse;

import java.util.ArrayList;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuidViewHolder>{
    private ArrayList<Alphabet> datalist = Warehouse.getAlphabetArrayList();

    @NonNull
    @Override
    public GuidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_holder,parent
                ,false);
        return new GuidViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidViewHolder holder, int position) {
        Alphabet curralph = datalist.get(position);
        holder.letter.setText(curralph.getLetter());
        holder.ruleDetail.setText(curralph.getRule());
        holder.engExa.setText(curralph.getEnglishExample());
        holder.nooExa.setText(curralph.getNoongarExample());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class GuidViewHolder extends RecyclerView.ViewHolder {

        private TextView letter;
        private TextView ruleDetail;
        private TextView nooExa;
        private TextView engExa;

        public GuidViewHolder(@NonNull View itemView) {
            super(itemView);
            letter = itemView.findViewById(R.id.guide_holder_letter);
            ruleDetail = itemView.findViewById(R.id.guide_holder_ruledetail);
            nooExa = itemView.findViewById(R.id.guide_holder_nooexa);
            engExa = itemView.findViewById(R.id.guide_holder_engexa);
        }
    }


}
