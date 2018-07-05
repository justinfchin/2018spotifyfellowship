package com.justinfchin.calendarapp.retrofit;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNetwork {

    //Local host
    private static final String BASE_URL = "http://10.0.2.2:3000";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public static ApiService getService(){
        if (retrofit == null) {
            Log.d("RetrofitNetwork", "Use getClient before Retrofit");
        }
        return retrofit.create(ApiService.class);
    }
}
