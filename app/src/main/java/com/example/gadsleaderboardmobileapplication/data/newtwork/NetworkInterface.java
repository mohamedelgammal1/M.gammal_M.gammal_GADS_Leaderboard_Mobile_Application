package com.example.gadsleaderboardmobileapplication.data.newtwork;

import com.example.gadsleaderboardmobileapplication.datamodels.HoursModel;
import com.example.gadsleaderboardmobileapplication.datamodels.ScoreModel;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NetworkInterface {
    @GET("/api/skilliq")
    Call<ArrayList<ScoreModel>> getTopScoresResponse();

    @GET("/api/hours")
    Call<ArrayList<HoursModel>> getTopHoursResponse();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> sendUserData(@Field("entry.1877115667") String firstName,
                                    @Field("entry.2006916086") String lastName,
                                    @Field("entry.1824927963") String email,
                                    @Field("entry.284483984") String projectLink);

}
