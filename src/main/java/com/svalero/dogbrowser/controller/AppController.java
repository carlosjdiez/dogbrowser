package com.svalero.dogbrowser.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    @FXML
    public TextField breedInput;

    @FXML
    public Button btSearch;

    @FXML
    private TabPane tpSearches;

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {
        tpSearches.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
    }

    @FXML
    public void searchBreed(ActionEvent event) {
        String requestedBreed = breedInput.getText().trim().toLowerCase();
        breedInput.clear();
        breedInput.requestFocus();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/svalero/dogbrowser/breed-view.fxml"));
        BreedController controller = new BreedController(requestedBreed);
        loader.setController(controller);

        try {
            Tab newTab = new Tab(requestedBreed, loader.load());
            tpSearches.getTabs().add(newTab);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
