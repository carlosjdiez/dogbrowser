package com.svalero.dogbrowser.controller;

import com.svalero.dogbrowser.task.BreedImagesTask;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class BreedController implements Initializable {

    @FXML
    private ListView<String> breedListView;

    @FXML
    private ImageView breedImageView;  // Imagen que se mostrará en la interfaz

    @FXML
    private TextField searchFilterInput; // Campo de texto para buscar

    private ObservableList<String> images;
    private String breed;

    public BreedController(String breed) {
        this.breed = breed;
        this.images = FXCollections.observableArrayList();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        breedListView.setItems(images);
        breedListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Cuando el usuario selecciona una imagen en la lista
            loadImage(newValue);
        });

        // Llamamos a la tarea en segundo plano para cargar las imágenes
        BreedImagesTask task = new BreedImagesTask(breed, images);
        new Thread(task).start();

        // Filtrar las imágenes según el texto ingresado en el campo de búsqueda
        searchFilterInput.textProperty().addListener((observable, oldValue, newValue) -> {
            filterImages(newValue);
        });
    }

    // Metodo para filtrar las imágenes según el texto de búsqueda
    private void filterImages(String query) {
        if (query == null || query.isEmpty()) {
            breedListView.setItems(images);
        } else {
            ObservableList<String> filteredImages = FXCollections.observableArrayList();
            for (String imageUrl : images) {
                if (imageUrl.contains(query.toLowerCase())) { // Filtra si el texto está en la URL de la imagen
                    filteredImages.add(imageUrl);
                }
            }
            breedListView.setItems(filteredImages); // Actualiza la lista con las imágenes filtradas
        }
    }

    private void loadImage(String imageUrl) {
        // Cargamos la imagen en el ImageView. Muestras las imagenes al hacer click en una raza
        Image image = new Image(imageUrl);
        breedImageView.setImage(image);
    }
}
