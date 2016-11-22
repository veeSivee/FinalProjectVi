package com.iak.vi.finalprojectvi.api;

import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.Datamovie;

import java.util.List;

import rx.Observable;


/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public interface DataSource {

    Observable<List<Datamovie>> getPopularMovie();

    Observable<DataTrailer> getTrailer(String id);
}
