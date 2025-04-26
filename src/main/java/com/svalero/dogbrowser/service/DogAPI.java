package com.svalero.dogbrowser.service;

import com.svalero.dogbrowser.model.BreedImagesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.Map;

public interface DogAPI {

    @GET("api/breeds/list/all")
    Observable<Map<String, Object>> getAllBreeds();

    @GET("api/breed/{breed}/images")
    Observable<BreedImagesResponse> getImagesByBreed(@Path("breed") String breed);
}
