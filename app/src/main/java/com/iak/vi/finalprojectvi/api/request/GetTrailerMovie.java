package com.iak.vi.finalprojectvi.api.request;

import com.iak.vi.finalprojectvi.UseCase;
import com.iak.vi.finalprojectvi.api.Repository;

import rx.Observable;

/**
 * Created by taufiqotulfaidah on 11/22/16.
 */

public class GetTrailerMovie extends UseCase{

    private Repository repository;

    private String id;

    public GetTrailerMovie(Repository repository){
        this.repository = repository;
    }

    public void setID(String id){
        this.id = id;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        return repository.getTrailer(id);
    }
}
