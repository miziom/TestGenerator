package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private JFXButton buttonAddSubject;

    @FXML
    private JFXButton buttonAddTest;

    @FXML
    private JFXButton buttonAddQuestion;

    @FXML
    private JFXButton buttonAddFile;

    @FXML
    private JFXButton buttonGenerate;

    @FXML
    private JFXButton buttonSettings;

    @FXML
    private JFXComboBox<Subject> ComboBoxSubject;

    @FXML
    private JFXComboBox<Test> ComboBoxTest;

    @FXML
    private JFXListView<Question> ListView;

    @FXML
    private Label LabelQuestionCounter;

    @FXML
    void ComboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void ComboBoxTestAction(ActionEvent event) {

    }

    @FXML
    void buttonAddFileAction(ActionEvent event) {

    }

    @FXML
    void buttonAddQuestionAction(ActionEvent event) {

    }

    @FXML
    void buttonAddSubjectAction(ActionEvent event) {

    }

    @FXML
    void buttonAddTestAction(ActionEvent event) {

    }

    @FXML
    void buttonGenerateAction(ActionEvent event) {

    }

    @FXML
    void buttonSettingsAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
