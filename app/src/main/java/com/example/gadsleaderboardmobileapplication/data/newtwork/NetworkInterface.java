package com.example.gadsleaderboardmobileapplication.data.newtwork;

import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkInterface {
    @GET("/api/skilliq")
    Call<ArrayList<ScoreModel>> getTopScoresResponse();

    @GET("/api/hours")
    Call<ArrayList<HoursModel>> getTopHoursResponse();

    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> sendUserData(@Body HashMap<String, String> hashMap);

}
