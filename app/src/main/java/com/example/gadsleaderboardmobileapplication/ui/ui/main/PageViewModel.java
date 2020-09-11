package com.example.gadsleaderboardmobileapplication.ui.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gadsleaderboardmobileapplication.data.RetroFitClient;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PageViewModel extends ViewModel {
    public MutableLiveData<ArrayList<ScoreModel>> mScoreMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<HoursModel>> mHoursMutableLiveData = new MutableLiveData<>();

    public void getTopScoresResponse() {
        RetroFitClient.getINSTANCE(false).getTopScoresResponse().
                enqueue(new Callback<ArrayList<ScoreModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<ScoreModel>> call, @NonNull Response<ArrayList<ScoreModel>> response) {
                        mScoreMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<ScoreModel>> call, @NonNull Throwable t) {
                    }
                });
    }

    public void getTopHoursResponse() {
        RetroFitClient.getINSTANCE(false).getTopHoursResponse().
                enqueue(new Callback<ArrayList<HoursModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<ArrayList<HoursModel>> call, @NonNull Response<ArrayList<HoursModel>> response) {
                        mHoursMutableLiveData.setValue(response.body());
                    }

                    @Override
                    public void onFailure(@NonNull Call<ArrayList<HoursModel>> call, @NonNull Throwable t) {
                    }
                });
    }

}