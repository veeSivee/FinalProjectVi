package com.iak.vi.finalprojectvi.main;

import com.iak.vi.finalprojectvi.data.Datamovie;
import com.iak.vi.finalprojectvi.data.PopularMovie;

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

        void selectFavoriteMovie(String listFav);

        void addListMovie(List<PopularMovie> listMovie);

    }

    interface View{

        void onGetPopularMovieSuccess(List<Datamovie> datamovies);

        void onGetPopularMovieFailed();

        void onGetTopRatedMovieSuccess(List<Datamovie> datamovies);

        void onGetTopratedMovieFailed();

        void onGetFavoriteMovieSuccess(List<PopularMovie> datamovies);

        void onGetFavoriteMovieFailed();

        void onGetDetailMovieSuccess(List<Datamovie> datamovies);

        void onGetDetailMovieFailed();

        void gotoDetailMovie(Datamovie datamovie);

        void onAddFavoriteMovie(PopularMovie popularMovie);

        void onRemoveFavoriteMovie(PopularMovie popularMovie);

        void showLoader();

        void hideLoader();

    }
}
