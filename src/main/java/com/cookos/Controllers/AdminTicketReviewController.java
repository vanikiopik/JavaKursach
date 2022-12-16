package com.cookos.Controllers;

import com.cookos.Client;
import com.cookos.Entities.Message;
import com.cookos.Server;
import com.mysql.cj.xdevapi.ClientImpl;
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
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class AdminTicketReviewController {

    public ComboBox<Integer> ticketIDComboBox;
    public TextArea questionTextArea;
    public TextArea answerTextArea;
    public Button backButton;
    public Button sendButton;

    private ObservableList<Message> messageList = FXCollections.observableArrayList();
    private ObservableList<Integer> messageIDs = FXCollections.observableArrayList();
    List<Message> ticketsList = null;


    @FXML
    private void initialize() throws IOException, ClassNotFoundException {
        Client.ostream.writeObject("SendingAllTicketsToReview");

        ticketsList = (List<Message>) Client.istream.readObject();
        for(var s : ticketsList){
            messageList.add(s);
        }
        for (var s : messageList){
            messageIDs.add(s.getMessageID());
        }
        ticketIDComboBox.setItems(messageIDs);
    }

    public void onSelectedValue(ActionEvent event) throws IOException {
        var choiceMessageId = (Integer)ticketIDComboBox.getSelectionModel().getSelectedItem();
        for (var s : ticketsList)
            if(choiceMessageId == s.getMessageID())
            {
                answerTextArea.setText(s.getAnswer());
                questionTextArea.setText(s.getQuestion());
            }
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

    public void onSendButtonClick(ActionEvent event) throws IOException {
        if(answerTextArea.getText().isBlank() |
            questionTextArea.getText().isBlank()){
            return;
        }
        else{
            Client.ostream.writeObject("AcceptAdminAnswer");
            Client.ostream.flush();


            Client.ostream.writeObject(ticketIDComboBox.getValue());
            Client.ostream.flush();
            Client.ostream.writeObject(answerTextArea.getText());
            Client.ostream.flush();

            Stage stage;
            Parent root;
            stage = (Stage) backButton.getScene().getWindow();
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/cookos/AdminTicketReview.fxml")));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }
}
