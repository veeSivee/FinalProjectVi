package com.iak.vi.finalprojectvi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ListMovie extends AppCompatActivity {

    private RecyclerView rvListMovie;
    private ListMovieAdapter listMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_movie);

        init();

        addDummyData();
    }

    private void init(){
        rvListMovie = (RecyclerView)findViewById(R.id.rvListMovie);

        GridLayoutManager gridLayoutVertical = new GridLayoutManager(this,2);
        rvListMovie.setHasFixedSize(true);
        rvListMovie.setLayoutManager(gridLayoutVertical);

        listMovieAdapter = new ListMovieAdapter(this);
        rvListMovie.setAdapter(listMovieAdapter);
    }

    private void addDummyData(){
        listMovieAdapter.clearAllItem();
        for (int i = 0; i < 5; i++){
            listMovieAdapter.addItem(i,"http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg");
        }
    }
}
