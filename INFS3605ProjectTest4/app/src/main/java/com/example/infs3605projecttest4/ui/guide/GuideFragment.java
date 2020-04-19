package com.example.infs3605projecttest4.ui.guide;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infs3605projecttest4.R;

public class GuideFragment extends Fragment {

    private GuideViewModel dashboardViewModel;
    private RecyclerView guide_rv;
    private Activity currActivity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(GuideViewModel.class);
        View root = inflater.inflate(R.layout.fragment_guide, container, false);

        currActivity = getActivity();
        guide_rv = root.findViewById(R.id.guide_rv);
        guide_rv.setLayoutManager(new LinearLayoutManager(currActivity, LinearLayoutManager.HORIZONTAL
                , false));
        GuideAdapter ad = new GuideAdapter();
        guide_rv.setAdapter(ad);

        return root;
    }
}
