package com.iak.vi.finalprojectvi.api.request;

import android.util.Log;

import com.iak.vi.finalprojectvi.api.ApiClient;
import com.iak.vi.finalprojectvi.api.ApiService;
import com.iak.vi.finalprojectvi.api.DataSource;
import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.Datamovie;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class MovieDataSource implements DataSource{

    private ApiClient apiClient;
    private Call<Datamovie> requestDataMovie;
    private Call<DataTrailer> requestDataTrailer;

    public MovieDataSource(){
        apiClient = ApiService.createService(ApiClient.class);

    }

    @Override
    public Observable<List<Datamovie>> getPopularMovie() {

        return Observable.create(new Observable.OnSubscribe<List<Datamovie>>() {
            @Override
            public void call(final Subscriber<? super List<Datamovie>> subscriber) {

                final List<Datamovie> datamovieList = new ArrayList<Datamovie>();
                final Throwable tr;
                requestDataMovie = apiClient.getPopularMovie();
                requestDataMovie.enqueue(new Callback<Datamovie>() {
                    @Override
                    public void onResponse(Call<Datamovie> call, Response<Datamovie> response) {
                        //Log.e("response","response \n" + response.headers());
                        String str = call.request().url().toString();
                        Datamovie datamovie = response.body();

                        if(datamovie != null){
                            datamovieList.add(datamovie);
                        }
                        datamovieList.add(datamovie);


                        if (datamovieList.size()>0){
                            subscriber.onNext(datamovieList);
                        }

                        subscriber.onCompleted();
                        //subscriber.onNext(datamovieList);
                    }

                    @Override
                    public void onFailure(Call<Datamovie> call, Throwable t) {
                        datamovieList.clear();

                        subscriber.onError(t);
                        subscriber.onCompleted();
                    }
                });

            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<DataTrailer> getTrailer(final String id) {
        return Observable.create(new Observable.OnSubscribe<DataTrailer>() {
            @Override
            public void call(final Subscriber<? super DataTrailer> subscriber) {

                requestDataTrailer = apiClient.getTrailer(id);
                requestDataTrailer.enqueue(new Callback<DataTrailer>() {
                    @Override
                    public void onResponse(Call<DataTrailer> call, Response<DataTrailer> response) {

                        String str = call.request().url().toString();
                        DataTrailer dataTrailer = response.body();

                        if (dataTrailer != null){
                            subscriber.onNext(dataTrailer);
                            subscriber.onCompleted();
                        }
                    }

                    @Override
                    public void onFailure(Call<DataTrailer> call, Throwable t) {
                        subscriber.onError(t);
                        subscriber.onCompleted();
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }
}
