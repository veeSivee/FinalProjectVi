package com.iak.vi.finalprojectvi.api;

import com.iak.vi.finalprojectvi.BuildConfig;
import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.Datamovie;
import com.iak.vi.finalprojectvi.util.ConstantData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by taufiqotulfaidah on 20/11/2016.
 */

public interface ApiClient {

    @GET(ConstantData.POPULAR_URL)
    Call<Datamovie> getPopularMovie();

    @GET(ConstantData.DATA_TRAILER_URL)
    Call<DataTrailer> getTrailer(@Path("id") String ID);


    @GET(ConstantData.TOP_RATED_URL)
    Call<Datamovie> getTopRatedMovie();
}
