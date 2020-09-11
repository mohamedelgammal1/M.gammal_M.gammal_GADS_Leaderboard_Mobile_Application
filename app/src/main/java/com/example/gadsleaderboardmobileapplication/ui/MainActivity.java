package com.example.gadsleaderboardmobileapplication.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.databinding.ActivityMainBinding;
import com.example.gadsleaderboardmobileapplication.ui.submit.SubmitActivity;
import com.example.gadsleaderboardmobileapplication.ui.ui.main.PageViewModel;
import com.example.gadsleaderboardmobileapplication.ui.ui.main.SectionsPagerAdapter;
import com.example.gadsleaderboardmobileapplication.utils.ConnectivityManager;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

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
    }

    private void vHandleData() {
        pageViewModel.getTopHoursResponse();
        activityMainBinding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (Objects.requireNonNull(Objects.requireNonNull(tab.getText()).toString())) {
                    case "Learning Leaders":
                        if (ConnectivityManager.CheckInternet(MainActivity.this)) {
                            pageViewModel.getTopHoursResponse();
                        }
                        break;
                    case "Skill IQ Leaders":
                        pageViewModel.getTopScoresResponse();
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
        activityMainBinding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (ConnectivityManager.CheckInternet(MainActivity.this)) {
                            pageViewModel.getTopHoursResponse();
                        }
                        break;
                    case 1:
                        pageViewModel.getTopScoresResponse();
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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