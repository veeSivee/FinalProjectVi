package com.iak.vi.finalprojectvi.main;

import android.util.Log;

import com.iak.vi.finalprojectvi.Injector;
import com.iak.vi.finalprojectvi.api.DataSource;
import com.iak.vi.finalprojectvi.api.request.GetPopularMovie;
import com.iak.vi.finalprojectvi.api.request.GetTopRatedMovie;
import com.iak.vi.finalprojectvi.data.Datamovie;
import com.iak.vi.finalprojectvi.data.PopularMovie;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class ListMoviePresenter implements ListMovieContract.Presenter{

    private ListMovieContract.View view;

    private GetPopularMovie getPopularMovie;

    private GetTopRatedMovie getTopRatedMovie;

    private List<PopularMovie> listAllMovie;

    private boolean isFirstLoad = false;

    public ListMoviePresenter(ListMovieContract.View view){
        this.view = view;
        getPopularMovie = new GetPopularMovie(Injector.provideRepository());
        getTopRatedMovie = new GetTopRatedMovie(Injector.provideRepository());

        listAllMovie = new ArrayList<>();
    }

    @Override
    public void start() {
        isFirstLoad = true;
        selectTopRatedMovie();
    }

    @Override
    public void clickSingleMovie() {

    }

    @Override
    public void clickFavorite() {

    }

    @Override
    public void selectPopularMovie() {

        getPopularMovie.execute(new Subscriber<List<Datamovie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Datamovie> datamovies) {
                view.onGetPopularMovieSuccess(datamovies);
            }
        });
    }

    @Override
    public void selectTopRatedMovie() {
        getTopRatedMovie.execute(new Subscriber<List<Datamovie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Datamovie> datamovies) {
                view.onGetTopRatedMovieSuccess(datamovies);

                if(isFirstLoad){
                    selectPopularMovie();
                    isFirstLoad = false;
                }
            }
        });
    }

    @Override
    public void selectFavoriteMovie(String listFav) {

        String[] favList = listFav.split(",");
        List<PopularMovie> listFavMovie = new ArrayList<>();


        for (PopularMovie popularMovie : listAllMovie){

            boolean isMatch = false;

            for (String idFav : favList){

                if(String.valueOf(popularMovie.getId()).equals(idFav)){
                    isMatch = true;
                }
            }

            if(isMatch){
                popularMovie.setFavorite(true);
                listFavMovie.add(popularMovie);
            }
        }

        view.onGetFavoriteMovieSuccess(listFavMovie);

    }

    @Override
    public void addListMovie(List<PopularMovie> listMovie) {

        for (PopularMovie popularMovie : listMovie){

            boolean isExist = false;

            for (PopularMovie popularMovie1 : listAllMovie){

                if(popularMovie.getId() == popularMovie1.getId()){
                    isExist = true;
                }
            }

            if(!isExist){
                listAllMovie.add(popularMovie);
            }
        }
    }
}
