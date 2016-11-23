package com.iak.vi.finalprojectvi.util;

import com.iak.vi.finalprojectvi.BuildConfig;

/**
 * Created by taufiqotulfaidah on 11/23/16.
 */

public class ConstantData {

    public static final String BASE_URL = "http://api.themoviedb.org";

    public static final String POPULAR_URL = "/3/movie/popular?api_key=" + BuildConfig.MOVIE_API_KEY;

    public static final String DATA_TRAILER_URL = "/3/movie/{id}/videos?api_key=" + BuildConfig.MOVIE_API_KEY;

    public static final String TOP_RATED_URL = "/3/movie/top_rated?api_key=" + BuildConfig.MOVIE_API_KEY;

    public static final String YOUTUBE_CHANNEL_URL = "http://www.youtube.com/watch?v=";

    public static final String PATH_IMAGE_MOVIE = "http://image.tmdb.org/t/p/w185/";
}
