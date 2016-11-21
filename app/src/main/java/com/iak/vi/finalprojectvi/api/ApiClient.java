package com.iak.vi.finalprojectvi.api;

import com.iak.vi.finalprojectvi.data.Datamovie;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by User_PC on 20/11/2016.
 */

public interface ApiClient {

    @GET("/3/movie/popular?api_key=2343de58f84ee178878b612179f0c0a4")
    Call<Datamovie> getPopularMovie();

}
