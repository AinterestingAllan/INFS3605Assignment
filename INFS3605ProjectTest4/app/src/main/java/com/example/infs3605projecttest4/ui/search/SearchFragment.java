package com.example.infs3605projecttest4.ui.search;

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

import com.example.infs3605projecttest4.Model.Word;
import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.database.Warehouse;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                ViewModelProviders.of(this).get(SearchViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_search, container, false);

        final TextView ser_english = root.findViewById(R.id.ser_english);
        final TextView ser_noongar = root.findViewById(R.id.ser_noongar);
        final TextView ser_type = root.findViewById(R.id.ser_type);
        final ImageView ser_image = root.findViewById(R.id.ser_image);

        final EditText ser_input = root.findViewById(R.id.ser_input);
        Button ser_serbt = root.findViewById(R.id.ser_serbt);

        ser_serbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String english = (ser_input.getText()+"").toLowerCase();
                Word serWord = Warehouse.searchWordByEnglish(english);
                if (serWord != null) {
                    ser_english.setText(serWord.getEnglish());
                    ser_noongar.setText(serWord.getLocal());
                    ser_type.setText(serWord.getType());
                    ser_image.setImageResource(serWord.getImage());
                } else {
                    Toast.makeText(getContext(), "No this word :(, try another!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }
}
