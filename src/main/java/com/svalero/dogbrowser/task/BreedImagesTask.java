package com.svalero.dogbrowser.task;

import com.svalero.dogbrowser.model.BreedImagesResponse;
import com.svalero.dogbrowser.service.DogService;
import io.reactivex.functions.Consumer;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

public class BreedImagesTask extends Task<Void> {

    private String breed;
    private ObservableList<String> imageUrls;

    public BreedImagesTask(String breed, ObservableList<String> imageUrls) {
        this.breed = breed;
        this.imageUrls = imageUrls;
    }

    @Override
    protected Void call() throws Exception {
        DogService service = new DogService();
        Consumer<BreedImagesResponse> user = (response) -> {
            Platform.runLater(() -> {
                imageUrls.clear();
                imageUrls.addAll(response.getMessage());
            });
        };
        service.getImagesByBreed(breed).subscribe(user);
        return null;
    }
}
