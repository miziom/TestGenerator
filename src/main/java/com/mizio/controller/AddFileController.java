package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddFileController implements Initializable {

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<?> comboBoxSubject;

    @FXML
    private JFXComboBox<?> comboBoxTest;

    @FXML
    private JFXRadioButton radioComboBox;

    @FXML
    private ToggleGroup radio;

    @FXML
    private JFXRadioButton radioTextField;

    @FXML
    private JFXTextField textField;

    @FXML
    private JFXButton buttonAddFile;

    @FXML
    void buttonAddFileAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {

    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {

    }

    @FXML
    void radioComboBoxAction(ActionEvent event) {

    }

    @FXML
    void radioTextFieldAction(ActionEvent event) {

    }

    @FXML
    void textFieldIsLetter(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
