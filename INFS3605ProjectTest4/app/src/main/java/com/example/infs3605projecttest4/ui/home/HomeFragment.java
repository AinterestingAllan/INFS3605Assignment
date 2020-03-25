package com.example.infs3605projecttest4.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.R;
import com.example.infs3605projecttest4.TypeAdapter;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.main_text1);
        RecyclerView main_rv = root.findViewById(R.id.main_rv);
        Context currentActivity = getActivity();
        main_rv.setLayoutManager(new LinearLayoutManager(currentActivity));
        TypeAdapter myAdapter = new TypeAdapter(currentActivity);
        main_rv.setAdapter(myAdapter);
        return root;
    }
}
