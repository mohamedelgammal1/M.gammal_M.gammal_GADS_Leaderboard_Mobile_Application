package com.example.gadsleaderboardmobileapplication.data;

import com.example.gadsleaderboardmobileapplication.data.newtwork.NetworkInterface;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetroFitClient {
    private final NetworkInterface networkInterface;
    private static RetroFitClient INSTANCE;

    private RetroFitClient() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceBaseUrl.BASE_URL) //BASE_URL
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
}
