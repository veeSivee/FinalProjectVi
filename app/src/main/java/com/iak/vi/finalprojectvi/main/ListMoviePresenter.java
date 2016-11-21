package com.iak.vi.finalprojectvi.main;

import android.util.Log;

import com.iak.vi.finalprojectvi.Injector;
import com.iak.vi.finalprojectvi.api.DataSource;
import com.iak.vi.finalprojectvi.api.request.GetPopularMovie;
import com.iak.vi.finalprojectvi.data.Datamovie;

import java.util.List;

import rx.Subscriber;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class ListMoviePresenter implements ListMovieContract.Presenter{

    private ListMovieContract.View view;

    private GetPopularMovie getPopularMovie;

    public ListMoviePresenter(ListMovieContract.View view){
        this.view = view;
        getPopularMovie = new GetPopularMovie(Injector.provideRepository());
    }

    @Override
    public void start() {

        getPopularMovie.execute(new Subscriber<List<Datamovie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Datamovie> datamovies) {
                Log.e("successs","success " + datamovies.size());
                view.onGetPopularMovieSuccess(datamovies);
            }
        });

    }

    @Override
    public void clickSingleMovie() {

    }

    @Override
    public void clickFavorite() {

    }

    @Override
    public void selectPopularMovie() {

    }

    @Override
    public void selectTopRatedMovie() {

    }

    @Override
    public void selectFavoriteMovie() {

    }
}
