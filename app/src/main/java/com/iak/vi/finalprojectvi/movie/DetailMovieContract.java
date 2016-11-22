package com.iak.vi.finalprojectvi.movie;

import com.iak.vi.finalprojectvi.data.DataTrailer;

/**
 * Created by taufiqotulfaidah on 11/22/16.
 */

public interface DetailMovieContract {

    interface Presenter{

        void getDataTrailer(String id);

    }

    interface View{

        void onGetTrailerSuccess(DataTrailer dataTrailer);

        void onGetTrailerFailed(String message);
    }
}
