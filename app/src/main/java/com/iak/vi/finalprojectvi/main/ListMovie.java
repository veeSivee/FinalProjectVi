package com.iak.vi.finalprojectvi.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.Datamovie;

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

        addDummyData();
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

    private void addDummyData(){
        listMovieAdapter.clearAllItem();
        for (int i = 0; i < 5; i++){
            listMovieAdapter.addItem(i,"http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
        }
    }

    @Override
    public void onGetPopularMovieSuccess(List<Datamovie> datamovies) {

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
