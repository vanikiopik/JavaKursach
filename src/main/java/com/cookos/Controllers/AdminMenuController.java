package com.cookos.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AdminMenuController {

    public Button addNewProductButton;
    public Button reviewTicketsButton;
    public Button exitButton;
    public Button changeProductValueButton;
    public Button reviewOrdersButton;
    public Button calculateNetProfitButton;

    public void onNewProductButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) addNewProductButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AddNewProductAdmin.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onReviewTicketsButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminTicketReview.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onExitButtonOrderClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Authorization.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onChangeProductValueButtonClick(ActionEvent event) {

    }

    public void onReviewOrdersButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminReviewOrder.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onCalculateProfitButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/NetProfit.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
