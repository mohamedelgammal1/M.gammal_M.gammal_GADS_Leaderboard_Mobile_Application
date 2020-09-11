package com.example.gadsleaderboardmobileapplication.data;

import com.example.gadsleaderboardmobileapplication.data.newtwork.NetworkInterface;
import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroFitClient {
    private final NetworkInterface networkInterface;

    private RetroFitClient(boolean submit) {
        Retrofit retrofit;
        String baseURL;
        if (submit) {
            baseURL = "https://docs.google.com/forms/d/e/";
        } else {
            baseURL = "https://gadsapi.herokuapp.com";
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(getHTTPClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkInterface = retrofit.create(NetworkInterface.class);
    }

    public static RetroFitClient getINSTANCE(boolean submit) {
        return new RetroFitClient(submit);
    }

    private static OkHttpClient.Builder getHTTPClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        return new OkHttpClient.Builder().addInterceptor(logging);
    }

    public Call<ArrayList<ScoreModel>> getTopScoresResponse() {
        return networkInterface.getTopScoresResponse();
    }

    public Call<ArrayList<HoursModel>> getTopHoursResponse() {
        return networkInterface.getTopHoursResponse();
    }

    public Call<ResponseBody> sendUserData(String firstName, String lastName, String email, String projectLink) {
        return networkInterface.sendUserData(firstName, lastName, email, projectLink);
    }
}
