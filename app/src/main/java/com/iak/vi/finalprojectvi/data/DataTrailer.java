package com.iak.vi.finalprojectvi.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by taufiqotulfaidah on 11/22/16.
 */

public class DataTrailer {

    @SerializedName("results")
    ArrayList<DataTrailerDetail> trailerDetailArrayList;

    public ArrayList<DataTrailerDetail> getTrailerDetailArrayList() {
        return trailerDetailArrayList;
    }

    public void setTrailerDetailArrayList(ArrayList<DataTrailerDetail> trailerDetailArrayList) {
        this.trailerDetailArrayList = trailerDetailArrayList;
    }
}
