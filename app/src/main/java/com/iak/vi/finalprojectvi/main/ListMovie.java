package com.iak.vi.finalprojectvi.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.Datamovie;
import com.iak.vi.finalprojectvi.data.PopularMovie;

import java.util.List;

public class ListMovie extends AppCompatActivity implements ListMovieContract.View{

    private RecyclerView rvListMovie;
    private ListMovieAdapter listMovieAdapter;

    private ListMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        init();
    }

    private void init(){
        rvListMovie = (RecyclerView)findViewById(R.id.rvListMovie);

        presenter = new ListMoviePresenter(this);

        GridLayoutManager gridLayoutVertical = new GridLayoutManager(this,2);
        rvListMovie.setHasFixedSize(true);
        rvListMovie.setLayoutManager(gridLayoutVertical);

        listMovieAdapter = new ListMovieAdapter(this);
        rvListMovie.setAdapter(listMovieAdapter);

        presenter.start();
    }

    @Override
    public void onGetPopularMovieSuccess(List<Datamovie> datamovies) {

        List<PopularMovie> popularMovieList = datamovies.get(0).getMovieArrayList();

        String path1 = "http://image.tmdb.org/t/p/w185/";

        for (PopularMovie popularMovie : popularMovieList){

            listMovieAdapter.addItem(listMovieAdapter.getItemCount(), path1,popularMovie);
        }

    }

    @Override
    public void onGetPopularMovieFailed() {

    }

    @Override
    public void onGetTopRatedMovieSuccess(List<Datamovie> datamovies) {

    }

    @Override
    public void onGetTopratedMovieFailed() {

    }

    @Override
    public void onGetFavoriteMovieSuccess(List<Datamovie> datamovies) {

    }

    @Override
    public void onGetFavoriteMovieFailed() {

    }

    @Override
    public void onGetDetailMovieSuccess(List<Datamovie> datamovies) {

    }

    @Override
    public void onGetDetailMovieFailed() {

    }

    @Override
    public void gotoDetailMovie(Datamovie datamovie) {

    }
}
