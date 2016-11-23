package com.iak.vi.finalprojectvi.api.request;

import com.iak.vi.finalprojectvi.UseCase;
import com.iak.vi.finalprojectvi.api.Repository;

import rx.Observable;

/**
 * Created by taufiqotulfaidah on 11/23/16.
 */

public class GetTopRatedMovie extends UseCase {

    private Repository repository;

    public GetTopRatedMovie(Repository repository){
        this.repository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getTopRated();
    }
}
