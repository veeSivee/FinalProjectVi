package com.iak.vi.finalprojectvi;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    ImageView ivLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setImage();

        gotoMainScreen();
    }

    private void init(){
        ivLauncher = (ImageView)findViewById(R.id.iv_launcher);
    }

    private void setImage(){
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg")
                .fit()
                .into(ivLauncher);
    }

    private void gotoMainScreen(){
        new CountDownTimer(3000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this,ListMovie.class);
                startActivity(intent);
            }
        }.start();
    }
}
