package com.example.gadsleaderboardmobileapplication.ui.submit;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gadsleaderboardmobileapplication.data.RetroFitClient;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmitViewModel extends ViewModel {
    public MutableLiveData<Integer> mSubmitMutableLiveData = new MutableLiveData<>();

    public void sendUserData(String firstName, String lastName, String email, String projectLink) {
        RetroFitClient.getINSTANCE(true).sendUserData(firstName, lastName, email, projectLink).
                enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            mSubmitMutableLiveData.setValue(1);
                        } else {
                            mSubmitMutableLiveData.postValue(-1);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        mSubmitMutableLiveData.postValue(-1);
                    }
                });
    }
}
