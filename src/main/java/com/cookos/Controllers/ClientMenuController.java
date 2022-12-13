package com.cookos.Controllers;

import com.cookos.Client;
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
    public Button deliveryPriceButton;
    public Button makeOrderButton;
    public Button watchOrderListButton;
    public Button makeTicketButton;
    public Button readAnswersButton;

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

    public void onDeliveryPriceClickButton(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) exitButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Delivery.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void onMakeOrderButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) makeOrderButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/Order.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onWatchOrderListButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) watchOrderListButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/OrderListWindow.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    public void onMakeTicketButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) makeTicketButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientTicketMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onReadAnsersButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) readAnswersButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientTicketMenuRead.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
