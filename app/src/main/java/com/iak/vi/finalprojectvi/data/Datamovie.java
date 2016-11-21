package com.iak.vi.finalprojectvi.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class Datamovie {

    @SerializedName("results")
    ArrayList<PopularMovie> movieArrayList = new ArrayList<>();

    public ArrayList<PopularMovie> getMovieArrayList() {
        return movieArrayList;
    }

    public void setMovieArrayList(ArrayList<PopularMovie> movieArrayList) {
        this.movieArrayList = movieArrayList;
    }
}
