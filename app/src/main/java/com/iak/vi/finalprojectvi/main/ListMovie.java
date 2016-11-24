package com.iak.vi.finalprojectvi.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    private SharedPreferences sharedPreferences;

    private ListMovieContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        setTitle("Popular Movie");

        initSharedPreference();

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

            case R.id.action_favorite:
                listMovieAdapter.clearAllItem();
                presenter.selectFavoriteMovie(getListSharedPref());
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

        listMovieAdapter = new ListMovieAdapter(this, this);
        rvListMovie.setAdapter(listMovieAdapter);

        listMovieAdapter.setIdFavoritemovie(getListSharedPref());

        presenter.start();
    }

    private void initSharedPreference(){
        sharedPreferences = getSharedPreferences(ConstantData.SHAREDPREFERENCE_REFERENCE, Context.MODE_PRIVATE);

    }

    private void showListMovie(List<Datamovie> datamovies){

        listMovieAdapter.clearAllItem();

        List<PopularMovie> popularMovieList = datamovies.get(0).getMovieArrayList();

        for (PopularMovie popularMovie : popularMovieList){

            listMovieAdapter.addItem(listMovieAdapter.getItemCount(), ConstantData.PATH_IMAGE_MOVIE,popularMovie);
        }

        presenter.addListMovie(popularMovieList);
    }

    private void showListMovie(List<PopularMovie> datamovies, boolean b){

        List<PopularMovie> popularMovieList = datamovies;

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
    public void onGetFavoriteMovieSuccess(List<PopularMovie> datamovies) {
        setTitle("Favorite Movie");
        showListMovie(datamovies, false);
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

    @Override
    public void onAddFavoriteMovie(PopularMovie popularMovie) {

        String restoreSharedpref = sharedPreferences.getString("List", null);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        String[] favList = null;
        StringBuilder sb = new StringBuilder();

        if(restoreSharedpref != null){
            favList = restoreSharedpref.split(",");

            for (int i = 0; i < favList.length; i++) {
                sb.append(favList[i]).append(",");
            }
        }

        sb.append(String.valueOf(popularMovie.getId())).append(",");

        editor.putString("List", sb.toString());
        editor.commit();

        listMovieAdapter.setIdFavoritemovie(getListSharedPref());
    }

    @Override
    public void onRemoveFavoriteMovie(PopularMovie popularMovie) {

        String restoreSharedpref = sharedPreferences.getString("List", null);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        StringBuilder sb = new StringBuilder();

        if(restoreSharedpref != null){
            String[] favList = restoreSharedpref.split(",");

            for (int i = 0; i < favList.length; i++) {

                if(!favList[i].toString().equals(String.valueOf(popularMovie.getId()))){
                    sb.append(favList[i]).append(",");
                }
            }
        }

        editor.putString("List", sb.toString());
        editor.commit();

        listMovieAdapter.setIdFavoritemovie(getListSharedPref());
    }

    private String getListSharedPref(){
        return sharedPreferences.getString("List", null);
    }
}
