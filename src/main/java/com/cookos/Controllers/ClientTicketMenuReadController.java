package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Entities.Message;
import com.cookos.Entities.Order;
import com.cookos.Entities.OrderHelper;
import com.cookos.Entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Set;

public class ClientTicketMenuReadController {

    public ComboBox questionComboBox;
    public TextArea anwserTextArea;
    public TextArea qustionTextArea;
    public Button backButton;

    private ObservableList<Message> messageList = FXCollections.observableArrayList();
    private ObservableList<Integer> messageIDs = FXCollections.observableArrayList();

    Set<Message> ticketsList = null;

    @FXML
    public void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("SendAllClientTickets");

        ticketsList = (Set<Message>)Client.istream.readObject();
        for(var s : ticketsList){
            messageList.add(s);
        }
        for (var s : messageList){
            messageIDs.add(s.getMessageID());
        }
        questionComboBox.setItems(messageIDs);


    }

    public void onBackButtonClick(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = (Stage) backButton.getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/ClientMenu.fxml")));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSelectedValue(ActionEvent event) {
        var choiceMessageId = (Integer)questionComboBox.getSelectionModel().getSelectedItem();
        for (var s : ticketsList)
            if(choiceMessageId == s.getMessageID()){
                anwserTextArea.setText(s.getAnswer());
                qustionTextArea.setText(s.getQuestion());
            }
    }
}
