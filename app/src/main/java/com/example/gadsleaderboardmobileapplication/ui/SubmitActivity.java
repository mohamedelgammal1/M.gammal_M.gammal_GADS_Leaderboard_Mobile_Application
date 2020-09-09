package com.example.gadsleaderboardmobileapplication.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.gadsleaderboardmobileapplication.R;
import com.example.gadsleaderboardmobileapplication.databinding.ActivitySubmitBinding;

public class SubmitActivity extends AppCompatActivity {
    ActivitySubmitBinding activitySubmitBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vInit();
        vHandleOnClickActions();
    }

    private void vHandleOnClickActions() {
        activitySubmitBinding.icBackIcon.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });
    }

    private void vInit() {
        /* binding initialization*/
        activitySubmitBinding = DataBindingUtil.setContentView(this, R.layout.activity_submit);
        activitySubmitBinding.setLifecycleOwner(this);
    }
}