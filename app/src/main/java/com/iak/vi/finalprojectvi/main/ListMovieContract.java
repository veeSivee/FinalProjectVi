package com.iak.vi.finalprojectvi.main;

import com.iak.vi.finalprojectvi.data.Datamovie;

import java.util.List;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public interface ListMovieContract {

    interface Presenter{

        void start();

        void clickSingleMovie();

        void clickFavorite();

        void selectPopularMovie();

        void selectTopRatedMovie();

        void selectFavoriteMovie();

    }

    interface View{

        void onGetPopularMovieSuccess(List<Datamovie> datamovies);

        void onGetPopularMovieFailed();

        void onGetTopRatedMovieSuccess(List<Datamovie> datamovies);

        void onGetTopratedMovieFailed();

        void onGetFavoriteMovieSuccess(List<Datamovie> datamovies);

        void onGetFavoriteMovieFailed();

        void onGetDetailMovieSuccess(List<Datamovie> datamovies);

        void onGetDetailMovieFailed();

        void gotoDetailMovie(Datamovie datamovie);

    }
}
