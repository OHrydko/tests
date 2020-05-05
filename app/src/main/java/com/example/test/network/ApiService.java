package com.example.test.network;

import com.example.test.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("test")
    Observable<Response> getData();
}
