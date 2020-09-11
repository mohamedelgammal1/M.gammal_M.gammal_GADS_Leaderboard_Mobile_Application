package com.example.gadsleaderboardmobileapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.databinding.ActivityMainBinding;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;
import com.example.gadsleaderboardmobileapplication.ui.submit.SubmitActivity;
import com.example.gadsleaderboardmobileapplication.ui.ui.main.PageViewModel;
import com.example.gadsleaderboardmobileapplication.ui.ui.main.SectionsPagerAdapter;
import com.example.gadsleaderboardmobileapplication.utils.ConnectivityManager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.gadsleaderboardmobileapplication.ui.ui.main.PlaceholderFragment.vHandleOnSuccessForGetHours;
import static com.example.gadsleaderboardmobileapplication.ui.ui.main.ScoresFragment.vHandleOnSuccessForGetScores;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private PageViewModel pageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vInit();
        vHandleSumbitClick();
    }

    private void vHandleSumbitClick() {
        activityMainBinding.btnSubmit.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
            startActivity(intent);
        });
    }

    private void vInit() {
        /* binding initialization*/
        vInitBinding();
        /* SectionsPagerAdapter initialization*/
        vInitPager();
        /* SectionsPagerAdapter selection*/
        vHandleData();
        vHandleObservers();
    }

    private void vHandleObservers() {
        pageViewModel.mHoursMutableLiveData.observe(this, this::vHandleOnSuccessForGetHoursMain);
        pageViewModel.mScoreMutableLiveData.observe(this, this::vHandleOnSuccessForGetScoresMain);
    }

    private void vHandleOnSuccessForGetScoresMain(ArrayList<ScoreModel> scoreModels) {
        activityMainBinding.progressBar.setVisibility(View.GONE);
        vHandleOnSuccessForGetScores(scoreModels);
    }

    private void vHandleOnSuccessForGetHoursMain(ArrayList<HoursModel> hoursModels) {
        activityMainBinding.progressBar.setVisibility(View.GONE);
        vHandleOnSuccessForGetHours(hoursModels);
    }

    private void vHandleData() {
        vGetHours();
        activityMainBinding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (Objects.requireNonNull(Objects.requireNonNull(tab.getText()).toString())) {
                    case "Learning Leaders":
                        vGetHours();
                        break;
                    case "Skill IQ Leaders":
                        vGetScores();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void vGetScores() {
        if (ConnectivityManager.CheckInternet(MainActivity.this)) {
            activityMainBinding.progressBar.setVisibility(View.VISIBLE);
            pageViewModel.getTopScoresResponse();
        }
    }

    private void vGetHours() {
        if (ConnectivityManager.CheckInternet(MainActivity.this)) {
            activityMainBinding.progressBar.setVisibility(View.VISIBLE);
            pageViewModel.getTopHoursResponse();
        }
    }

    private void vInitPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        activityMainBinding.viewPager.setAdapter(sectionsPagerAdapter);
        activityMainBinding.tabs.setupWithViewPager(activityMainBinding.viewPager);
    }

    private void vInitBinding() {
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setLifecycleOwner(this);
        ViewModelProvider.Factory factory = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, factory);
        pageViewModel = viewModelProvider.get(PageViewModel.class);
    }
}