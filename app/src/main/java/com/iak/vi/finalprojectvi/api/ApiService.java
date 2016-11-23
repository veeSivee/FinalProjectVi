package com.iak.vi.finalprojectvi.api;

import android.text.format.Time;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iak.vi.finalprojectvi.util.ConstantData;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by taufiqotulfaidah on 20/11/2016.
 */

public class ApiService {

    public static <T> T createService(Class<T> serviceClass){
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder().create();

        Retrofit builder = new Retrofit.Builder()
                .baseUrl(ConstantData.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return builder.create(serviceClass);
    }
}
