package com.example.gadsleaderboardmobileapplication.ui.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboardmobileapplication.databinding.FragmentScoresBinding;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;

public class ScoresFragment extends Fragment {

    private static ItemsAdapter itemsAdapter;
    static FragmentScoresBinding fragmentScoresBinding;
    static FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        fragmentScoresBinding = FragmentScoresBinding.inflate(
                inflater, container, false);
        View root = fragmentScoresBinding.getRoot();
        activity = getActivity();
        return root;
    }


    public static void vHandleOnSuccessForGetScores(ArrayList<ScoreModel> scoreModels) {
        fragmentScoresBinding.itemsRecyclerview.setAdapter(null);
        PopulateRecyclerView("SCORES");
        itemsAdapter.setScoresModelArrayList(scoreModels);
        fragmentScoresBinding.itemsRecyclerview.setAdapter(itemsAdapter);

    }

    public static void PopulateRecyclerView(String type) {
        itemsAdapter = new ItemsAdapter(activity, type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,
                RecyclerView.VERTICAL,
                false);
        fragmentScoresBinding.itemsRecyclerview.setLayoutManager(layoutManager);
        fragmentScoresBinding.itemsRecyclerview.setHasFixedSize(true);
        fragmentScoresBinding.itemsRecyclerview.setItemViewCacheSize(20);
        fragmentScoresBinding.itemsRecyclerview.setDrawingCacheEnabled(true);
        fragmentScoresBinding.itemsRecyclerview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        fragmentScoresBinding.itemsRecyclerview.setItemAnimator(new DefaultItemAnimator());
        fragmentScoresBinding.itemsRecyclerview.setAdapter(itemsAdapter);
    }
}