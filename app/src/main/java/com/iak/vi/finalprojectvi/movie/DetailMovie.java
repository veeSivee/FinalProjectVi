package com.iak.vi.finalprojectvi.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.iak.vi.finalprojectvi.R;
import com.iak.vi.finalprojectvi.data.PopularMovie;
import com.squareup.picasso.Picasso;

public class DetailMovie extends AppCompatActivity {

    private ImageView ivBanner, ivPoster;
    private TextView tvTitle, tvDescription;
    PopularMovie popularMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        init();

        showData();
    }

    private void init(){
        Bundle b = this.getIntent().getExtras();

        ivBanner = (ImageView) findViewById(R.id.iv_detail_movie_banner);
        ivPoster = (ImageView) findViewById(R.id.iv_detail_movie_poster);
        tvTitle = (TextView) findViewById(R.id.tv_detail_movie_title);
        tvDescription = (TextView) findViewById(R.id.tv_detail_description);

        if(b != null){
            popularMovie = b.getParcelable("movie");
        }
    }

    private void showData(){
        String path1 = "http://image.tmdb.org/t/p/w185/";

        showTitle(popularMovie.getTitle());
        showDescription(popularMovie.getOverview());
        showPoster(path1 + popularMovie.getPosterPath());
        showBanner(path1 + popularMovie.getBackdropPath());

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
}
