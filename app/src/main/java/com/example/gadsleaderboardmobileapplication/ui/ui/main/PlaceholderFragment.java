package com.example.gadsleaderboardmobileapplication.ui.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboardmobileapplication.databinding.FragmentMainBinding;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private ItemsAdapter itemsAdapter;
    FragmentMainBinding fragmentMainBinding;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vInitModel();
    }

    private void vInitModel() {
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        pageViewModel = viewModelProvider.get(PageViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        fragmentMainBinding = FragmentMainBinding.inflate(
                inflater, container, false);
        View root = fragmentMainBinding.getRoot();
        PopulateRecyclerView("HOURS");
        vHandleObservers();
        return root;
    }

    private void vHandleObservers() {
        pageViewModel.mHoursMutableLiveData.observe(getViewLifecycleOwner(), this::vHandleOnSuccessForGetHours);
        pageViewModel.mScoreMutableLiveData.observe(getViewLifecycleOwner(), this::vHandleOnSuccessForGetScores);
    }

    private void vHandleOnSuccessForGetScores(ArrayList<ScoreModel> scoreModels) {
        fragmentMainBinding.itemsRecyclerview.setAdapter(null);
        PopulateRecyclerView("SCORES");
        itemsAdapter.setScoresModelArrayList(scoreModels);
    }

    private void vHandleOnSuccessForGetHours(ArrayList<HoursModel> hoursModel) {
        fragmentMainBinding.itemsRecyclerview.setAdapter(null);
        PopulateRecyclerView("HOURS");
        itemsAdapter.setHoursModelArrayList(hoursModel);
    }

    private void PopulateRecyclerView(String type) {
        itemsAdapter = new ItemsAdapter(requireActivity(), type);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(),
                RecyclerView.HORIZONTAL,
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