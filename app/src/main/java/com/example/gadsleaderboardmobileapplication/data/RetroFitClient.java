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
    private static RetroFitClient INSTANCE;

    private RetroFitClient() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .client(getHTTPClient().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        networkInterface = retrofit.create(NetworkInterface.class);
    }

    public static RetroFitClient getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new RetroFitClient();
        }
        return INSTANCE;
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
