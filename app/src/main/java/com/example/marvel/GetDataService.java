package com.example.marvel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {

    @GET("/marvels-android/marvels.json")
    Call<List<Person>> getAllData();


}
