package com.Steven.movieApplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Steven on 6/2/2018.
 * model for the movie list.
 */

public class MovieResponse {

    @SerializedName("results")
    private List<MovieModel> results;


    public List<MovieModel> getResults() {
        return results;
    }


}
