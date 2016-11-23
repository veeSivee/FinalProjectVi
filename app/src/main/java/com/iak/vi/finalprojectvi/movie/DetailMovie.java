package com.iak.vi.finalprojectvi.movie;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.DataTrailerDetail;
import com.iak.vi.finalprojectvi.data.PopularMovie;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailMovie extends AppCompatActivity implements DetailMovieContract.View{

    private ImageView ivBanner, ivPoster;
    private TextView tvTitle, tvDescription, tvReleaseDate;
    private RatingBar rbRating;
    private RecyclerView rvListTrailer;
    PopularMovie popularMovie;

    private DetailMovieContract.Presenter presenter;
    private TrailerMovieAdapter trailerMovieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        init();

        showData();
    }

    private void init(){
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayOptions(0, android.support.v7.app.ActionBar.DISPLAY_SHOW_TITLE);
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setDisplayShowHomeEnabled(true);
        }

        Bundle b = this.getIntent().getExtras();

        presenter = new DetailMoviePresenter(this);

        ivBanner = (ImageView) findViewById(R.id.iv_detail_movie_banner);
        ivPoster = (ImageView) findViewById(R.id.iv_detail_movie_poster);
        tvTitle = (TextView) findViewById(R.id.tv_detail_movie_title);
        tvDescription = (TextView) findViewById(R.id.tv_detail_description);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        rbRating = (RatingBar) findViewById(R.id.rb_rating);
        rvListTrailer = (RecyclerView) findViewById(R.id.rv_list_trailer);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rvListTrailer.setHasFixedSize(true);
        rvListTrailer.setLayoutManager(gridLayoutManager);

        trailerMovieAdapter = new TrailerMovieAdapter(this);
        rvListTrailer.setAdapter(trailerMovieAdapter);

        if(b != null){
            popularMovie = b.getParcelable("movie");

            presenter.getDataTrailer(String.valueOf(popularMovie.getId()));
        }
    }

    private void showData(){
        try{
            String path1 = "http://image.tmdb.org/t/p/w185/";

            showTitle(popularMovie.getTitle());
            showReleaseDate(popularMovie.getReleaseDate());
            showDescription(popularMovie.getOverview());
            showRating(popularMovie.getVoteAverage()/2);
            showPoster(path1 + popularMovie.getPosterPath());
            showBanner(path1 + popularMovie.getBackdropPath());
        }catch (Exception e){

        }
    }

    private void showBanner(String pathBanner){
        Picasso.with(this)
                .load(pathBanner)
                .fit()
                .into(ivBanner);
    }

    private void showPoster(String pathPoster){
        Picasso.with(this)
                .load(pathPoster)
                .fit()
                .into(ivPoster);
    }

    private void showTitle(String title){
        tvTitle.setText(title);
    }

    private void showDescription(String description){
        tvDescription.setText(description);
    }

    private void showReleaseDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date datee = format.parse(date);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        String datetime = dateFormat.format(datee);

        tvReleaseDate.setText(datetime);
    }

    private void showRating(float rating){
        rbRating.setRating(rating);
    }

    @Override
    public void onGetTrailerSuccess(DataTrailer dataTrailer) {

        List<DataTrailerDetail> dataTrailerDetail = dataTrailer.getTrailerDetailArrayList();

        for (int i = 0; i < dataTrailerDetail.size() ; i++) {
            DataTrailerDetail dataTrailerDetail1 = dataTrailerDetail.get(i);
            trailerMovieAdapter.addItem( trailerMovieAdapter.getItemCount(),dataTrailerDetail1.getNameTrailer(), dataTrailerDetail1.getSiteTrailer());
        }
    }

    @Override
    public void onGetTrailerFailed(String message) {

    }
}
