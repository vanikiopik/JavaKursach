package com.cookos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NetProfitController {

    public Button backButton;
    public TextArea ordersPriceArea;
    public TextArea nerPriceArea;

    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("PricesRequest");

        float ordersPrice = (float) Client.istream.readObject();
        float netPrice = (float) Client.istream.readObject();

        ordersPriceArea.setText(String.valueOf(ordersPrice));

        nerPriceArea.setText(String.valueOf(netPrice));

    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
