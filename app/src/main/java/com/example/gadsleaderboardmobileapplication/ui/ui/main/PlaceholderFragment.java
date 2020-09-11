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

import com.example.gadsleaderboardmobileapplication.databinding.FragmentMainBinding;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static ItemsAdapter itemsAdapter;
    static FragmentMainBinding fragmentMainBinding;
    static FragmentActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(
                inflater, container, false);
        View root = fragmentMainBinding.getRoot();
        activity = getActivity();
        return root;
    }

    public static void vHandleOnSuccessForGetHours(ArrayList<HoursModel> hoursModel) {
        fragmentMainBinding.itemsRecyclerview.setAdapter(null);
        PopulateRecyclerView("HOURS");
        itemsAdapter.setHoursModelArrayList(hoursModel);
        fragmentMainBinding.itemsRecyclerview.setAdapter(itemsAdapter);
    }

    public static void PopulateRecyclerView(String type) {
        itemsAdapter = new ItemsAdapter(activity, type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity,
                RecyclerView.VERTICAL,
                false);
        fragmentMainBinding.itemsRecyclerview.setLayoutManager(layoutManager);
        fragmentMainBinding.itemsRecyclerview.setHasFixedSize(true);
        fragmentMainBinding.itemsRecyclerview.setItemViewCacheSize(20);
        fragmentMainBinding.itemsRecyclerview.setDrawingCacheEnabled(true);
        fragmentMainBinding.itemsRecyclerview.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        fragmentMainBinding.itemsRecyclerview.setItemAnimator(new DefaultItemAnimator());
        fragmentMainBinding.itemsRecyclerview.setAdapter(itemsAdapter);
    }
}