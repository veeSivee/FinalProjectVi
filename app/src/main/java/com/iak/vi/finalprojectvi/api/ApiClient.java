package com.iak.vi.finalprojectvi.api;

import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.Datamovie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by User_PC on 20/11/2016.
 */

public interface ApiClient {

    @GET("/3/movie/popular?api_key=2343de58f84ee178878b612179f0c0a4")
    Call<Datamovie> getPopularMovie();

    @GET("http://api.themoviedb.org/3/movie/{id}/videos?api_key=2343de58f84ee178878b612179f0c0a4")
    Call<DataTrailer> getTrailer(@Path("id") String ID);


    @GET("/3/movie/top_rated?api_key=2343de58f84ee178878b612179f0c0a4")
    Call<Datamovie> getTopRatedMovie();
}
