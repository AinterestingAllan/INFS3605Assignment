package com.example.infs3605projecttest4;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.database.ImportantData;
import com.example.infs3605projecttest4.Model.TestType;
import com.example.infs3605projecttest4.activity.SelectActivity;
import com.example.infs3605projecttest4.database.Warehouse;

import java.util.ArrayList;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.TypeViewHolder> {

    private Context currentActivity;
    private ArrayList<TestType> data = new ArrayList<>(Warehouse.getTypesMap().values());

    public TypeAdapter(Context currentActivity) {
        this.currentActivity = currentActivity;
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.type_holder,parent
                ,false);
        return new TypeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder holder, int position) {
        holder.typeName.setText(data.get(position).getName());
        // holder.typeName.setText(TestType.getTypeList().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TypeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView typeImage;
        private TextView typeName;

        TypeViewHolder(@NonNull View itemView) {
            super(itemView);
            typeImage = itemView.findViewById(R.id.type_image);
            typeName = itemView.findViewById(R.id.type_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(currentActivity, SelectActivity.class);
            ImportantData.setCurrExeType(Warehouse.getTypesMap().get(typeName.getText()));
            // ImportantData.setCurrExeType(TestType.getTypeList().get(getAdapterPosition()));
            currentActivity.startActivity(intent);
        }
    }
}
