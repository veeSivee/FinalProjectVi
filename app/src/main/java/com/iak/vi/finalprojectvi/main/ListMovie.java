package com.iak.vi.finalprojectvi.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.Datamovie;
import com.iak.vi.finalprojectvi.data.PopularMovie;
import com.iak.vi.finalprojectvi.util.ConstantData;

import java.util.List;

public class ListMovie extends AppCompatActivity implements ListMovieContract.View{

    private RecyclerView rvListMovie;
    private ListMovieAdapter listMovieAdapter;

    private ListMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        setTitle("Popular Movie");

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.action_popular:
                listMovieAdapter.clearAllItem();
                presenter.selectPopularMovie();
                break;

            case R.id.action_top_rated:
                listMovieAdapter.clearAllItem();
                presenter.selectTopRatedMovie();
                break;
        }

        return super.onOptionsItemSelected(item);
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

    private void showListMovie(List<Datamovie> datamovies){

        List<PopularMovie> popularMovieList = datamovies.get(0).getMovieArrayList();

        for (PopularMovie popularMovie : popularMovieList){

            listMovieAdapter.addItem(listMovieAdapter.getItemCount(), ConstantData.PATH_IMAGE_MOVIE,popularMovie);
        }
    }

    @Override
    public void onGetPopularMovieSuccess(List<Datamovie> datamovies) {
        setTitle("Popular Movie");
        showListMovie(datamovies);
    }

    @Override
    public void onGetPopularMovieFailed() {

    }

    @Override
    public void onGetTopRatedMovieSuccess(List<Datamovie> datamovies) {
        setTitle("Top Rated Movie");
        showListMovie(datamovies);
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
