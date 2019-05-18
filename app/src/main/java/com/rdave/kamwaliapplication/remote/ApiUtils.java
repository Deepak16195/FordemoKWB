package com.rdave.kamwaliapplication.remote;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by user on 07-09-2017.
 */

public class ApiUtils {

    public ApiUtils() {}

    public static final String BASE_URL = "http://srv.securebrandtech.com/api/";

    public static APIService getAPIService() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(1, TimeUnit.DAYS);
        okHttpClient.readTimeout(1, TimeUnit.DAYS);
        okHttpClient.writeTimeout(1, TimeUnit.DAYS);

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);

/*
        return new retrofit2.Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(BASE_URL)                                       // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(APIService.class);
*/
    }


}