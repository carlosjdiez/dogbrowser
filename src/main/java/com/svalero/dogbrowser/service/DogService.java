package com.svalero.dogbrowser.service;

import com.google.gson.GsonBuilder;
import com.svalero.dogbrowser.model.BreedImagesResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;

public class DogService {

    private static final String BASE_URL = "https://dog.ceo/";

    private DogAPI dogAPI;

    public DogService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        dogAPI = retrofit.create(DogAPI.class);
    }

    public Observable<Map<String, Object>> getAllBreeds() {
        return dogAPI.getAllBreeds();
    }

    public Observable<BreedImagesResponse> getImagesByBreed(String breed) {
        return dogAPI.getImagesByBreed(breed);
    }
}
