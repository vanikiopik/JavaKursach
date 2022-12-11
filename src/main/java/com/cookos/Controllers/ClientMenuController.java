package com.cookos.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ClientMenuController {
    public Button watchCatalogButton;
    public Button exitButton;

    public void onClickWatchCatalog(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) watchCatalogButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Catalog.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onClickExitButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Authorization.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
