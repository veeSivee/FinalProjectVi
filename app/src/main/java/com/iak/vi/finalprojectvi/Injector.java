package com.iak.vi.finalprojectvi;

import com.iak.vi.finalprojectvi.api.Repository;
import com.iak.vi.finalprojectvi.api.request.MovieDataSource;

/**
 * Created by taufiqotulfaidah on 11/21/16.
 */

public class Injector {

    private static Repository repository;

    private static MovieDataSource movieDataSource;

    public static final Repository provideRepository() {
        if (repository == null) {
            repository = new Repository(provideMovieDataSource());
        }
        return repository;
    }

    public static final MovieDataSource provideMovieDataSource(){

        if(movieDataSource == null){
            movieDataSource = new MovieDataSource();
        }

        return movieDataSource;
    }
}
