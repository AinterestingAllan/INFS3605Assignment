package com.example.infs3605projecttest4.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.activity.WordDetailActivity;
import com.example.infs3605projecttest4.database.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;
    private List<Word> dataFull;
    private SearchAdapter searchAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_search, container, false);


        final EditText ser_input = root.findViewById(R.id.ser_input);
        Button ser_serbt = root.findViewById(R.id.ser_serbt);
        final RecyclerView ser_rv = root.findViewById(R.id.ser_rv);
        new Thread() {
            public void run() {
                dataFull = Warehouse.getDb().wordDao().getWords();
                ser_rv.setLayoutManager(new LinearLayoutManager(getContext()));
                searchAdapter = new SearchAdapter(dataFull);
                searchAdapter.setCurrContext(getContext());
                ser_rv.setAdapter(searchAdapter);
            }
        }.start();


        ser_serbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Word> serWordList = new ArrayList<>();
                CharSequence input = ser_input.getText().toString().toLowerCase();
                if("".contains(input)) {
                    serWordList.addAll(dataFull);
                }else {
                    for(Word x : dataFull) {
                        if(x.getEnglish().toLowerCase().contains(input)) {
                            serWordList.add(x);
                        }
                    }
                }
                searchAdapter.setData(serWordList);
                searchAdapter.notifyDataSetChanged();

            }
        });

        return root;
    }
}
