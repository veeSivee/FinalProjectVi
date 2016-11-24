package com.iak.vi.finalprojectvi.movie;

import com.iak.vi.finalprojectvi.Injector;
import com.iak.vi.finalprojectvi.api.request.GetTrailerMovie;
import com.iak.vi.finalprojectvi.data.DataTrailer;

import rx.Subscriber;

/**
 * Created by taufiqotulfaidah on 11/22/16.
 */

public class DetailMoviePresenter implements DetailMovieContract.Presenter{

    private DetailMovieContract.View view;

    private GetTrailerMovie getTrailerMovie;

    public DetailMoviePresenter(DetailMovieContract.View view){
        this.view = view;
        getTrailerMovie = new GetTrailerMovie(Injector.provideRepository());
    }

    @Override
    public void getDataTrailer(String id) {
        view.showLoader();

        getTrailerMovie.setID(id);
        getTrailerMovie.execute(new Subscriber<DataTrailer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.onGetTrailerFailed(e.getMessage());
            }

            @Override
            public void onNext(DataTrailer dataTrailer) {
                view.onGetTrailerSuccess(dataTrailer);
                view.hideLoader();
            }
        });
    }
}
