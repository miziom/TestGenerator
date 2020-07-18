package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXColorPicker colorPicker;

    @FXML
    private JFXTextField textFieldGroupNameColor;

    @FXML
    private JFXButton buttonAddColor;

    @FXML
    void buttonAddColorAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void textFieldGroupNameColorAction(ActionEvent event) {

    }

    @FXML
    void textFieldGroupNameColorIsLetter(KeyEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
