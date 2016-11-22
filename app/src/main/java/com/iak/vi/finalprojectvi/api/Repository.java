package com.iak.vi.finalprojectvi.api;

import com.iak.vi.finalprojectvi.data.DataTrailer;
import com.iak.vi.finalprojectvi.data.Datamovie;

import java.util.List;

import rx.Observable;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class Repository implements DataSource{

    private DataSource dataSource;

    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<List<Datamovie>> getPopularMovie() {
        return dataSource.getPopularMovie();
    }

    @Override
    public Observable<DataTrailer> getTrailer(String id) {
        return dataSource.getTrailer(id);
    }
}
