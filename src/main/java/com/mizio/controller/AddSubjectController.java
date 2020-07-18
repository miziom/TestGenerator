package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {

    @FXML
    private JFXButton buttonBack;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> columnSubjectName;

    @FXML
    private JFXTextField textFieldSubjectName;

    @FXML
    private JFXButton buttonAddSubject;

    @FXML
    void buttonAddSubjectAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void isLetterAction(KeyEvent event) {

    }

    @FXML
    void textFieldSubjectNameAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
