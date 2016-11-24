package com.iak.vi.finalprojectvi.movie;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.DataTrailerDetail;
import com.iak.vi.finalprojectvi.data.PopularMovie;
import com.iak.vi.finalprojectvi.util.ConstantData;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailMovie extends AppCompatActivity implements DetailMovieContract.View, View.OnClickListener {

    private ImageView ivBanner, ivPoster;
    private TextView tvTitle, tvDescription, tvReleaseDate;
    private RatingBar rbRating;
    private RecyclerView rvListTrailer;
    private Toolbar toolbar;
    PopularMovie popularMovie;

    private DetailMovieContract.Presenter presenter;
    private TrailerMovieAdapter trailerMovieAdapter;

    private String urlTrailer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;

        if(width>height){
            setContentView(R.layout.activity_detail_movie_landscape);
        } else {
            setContentView(R.layout.activity_detail_movie);
        }

        init();

        initToolbar();

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

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        ivBanner = (ImageView) findViewById(R.id.iv_detail_movie_banner);
        ivPoster = (ImageView) findViewById(R.id.iv_detail_movie_poster);
        tvTitle = (TextView) findViewById(R.id.tv_detail_movie_title);
        tvDescription = (TextView) findViewById(R.id.tv_detail_description);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        rbRating = (RatingBar) findViewById(R.id.rb_rating);
        rvListTrailer = (RecyclerView) findViewById(R.id.rv_list_trailer);

        ivBanner.setOnClickListener(this);

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

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void showData(){
        try{
            showTitle(popularMovie.getTitle());
            showReleaseDate(popularMovie.getReleaseDate());
            showDescription(popularMovie.getOverview());
            showRating(popularMovie.getVoteAverage()/2);
            showPoster(ConstantData.PATH_IMAGE_MOVIE + popularMovie.getPosterPath());
            showBanner(ConstantData.PATH_IMAGE_MOVIE + popularMovie.getBackdropPath());
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

        urlTrailer = dataTrailerDetail.get(dataTrailerDetail.size()-1).getKeyTrailer();

        for (int i = 0; i < dataTrailerDetail.size() ; i++) {
            DataTrailerDetail dataTrailerDetail1 = dataTrailerDetail.get(i);
            trailerMovieAdapter.addItem( trailerMovieAdapter.getItemCount(),dataTrailerDetail1.getNameTrailer(), dataTrailerDetail1.getKeyTrailer());
        }
    }

    @Override
    public void onGetTrailerFailed(String message) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_detail_movie_banner:
                if(!TextUtils.isEmpty(urlTrailer)){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse( ConstantData.YOUTUBE_CHANNEL_URL + urlTrailer)));
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showLoader() {
        findViewById(R.id.loader).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        findViewById(R.id.loader).setVisibility(View.GONE);
    }
}
