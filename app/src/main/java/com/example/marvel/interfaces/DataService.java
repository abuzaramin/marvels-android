package com.example.marvel.interfaces;

import com.example.marvel.models.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {

    @GET("/marvels-android/marvels.json")
    Call<List<Person>> getAllData();


}
