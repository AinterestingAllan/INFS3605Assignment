package com.example.infs3605projecttest4.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.activity.WordDetailActivity;
import com.example.infs3605projecttest4.database.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    private List<Word> data;
    private Context currContext;

    public SearchAdapter(List<Word> data) {
        this.data = data;
    }

    public void setData(List<Word> data) {
        this.data = data;
    }

    public void setCurrContext(Context currContext) {
        this.currContext = currContext;
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_holder,parent
                ,false);
        return new SearchHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        holder.search_holder_text.setText(data.get(position).getEnglish());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class SearchHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView search_holder_text;

        public SearchHolder(@NonNull View itemView) {
            super(itemView);
            search_holder_text =  itemView.findViewById(R.id.search_holder_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Warehouse.setSerWord(data.get(getAdapterPosition()));
            Intent intent = new Intent(currContext, WordDetailActivity.class);
            currContext.startActivity(intent);
        }
    }


}
