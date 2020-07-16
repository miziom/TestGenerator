package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTestController implements Initializable {

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<?> comboBoxSubject;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> columnTestName;

    @FXML
    private JFXTextField textFieldTestName;

    @FXML
    private JFXButton buttonAddTest;

    @FXML
    void buttonAddTestAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {

    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void isLetterAction(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
