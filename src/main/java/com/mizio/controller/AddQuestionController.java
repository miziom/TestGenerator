package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<?> comboBoxSubject;

    @FXML
    private JFXComboBox<?> comboBoxTest;

    @FXML
    private JFXTextField textFieldQuestionName;

    @FXML
    private JFXRadioButton radioAB;

    @FXML
    private ToggleGroup RadioAnswerGroup;

    @FXML
    private JFXRadioButton radioABC;

    @FXML
    private JFXRadioButton radioABCD;

    @FXML
    private JFXButton buttonAddImg;

    @FXML
    private ImageView imgCheckmark;

    @FXML
    private JFXTextField textFieldAnswerA;

    @FXML
    private JFXRadioButton radioCorrectA;

    @FXML
    private ToggleGroup RadioCorrectGroup;

    @FXML
    private JFXTextField textFieldAnswerB;

    @FXML
    private JFXRadioButton radioCorrectB;

    @FXML
    private JFXTextField textFieldAnswerC;

    @FXML
    private JFXRadioButton radioCorrectC;

    @FXML
    private JFXTextField textFieldAnswerD;

    @FXML
    private JFXRadioButton radioCorrectD;

    @FXML
    private JFXButton buttonAddQuestion;

    @FXML
    void buttonAddImgAction(ActionEvent event) {

    }

    @FXML
    void buttonAddQuestionAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {

    }

    @FXML
    void imgCheckmarkMouseClicked(MouseEvent event) {

    }

    @FXML
    void radioABAction(ActionEvent event) {

    }

    @FXML
    void radioABCAction(ActionEvent event) {

    }

    @FXML
    void radioABCDAction(ActionEvent event) {

    }

    @FXML
    void radioCorrectAAction(ActionEvent event) {

    }

    @FXML
    void radioCorrectBAction(ActionEvent event) {

    }

    @FXML
    void radioCorrectCAction(ActionEvent event) {

    }

    @FXML
    void radioCorrectDAction(ActionEvent event) {

    }

    @FXML
    void textFieldAnswerAAction(ActionEvent event) {

    }

    @FXML
    void textFieldAnswerAisLetter(KeyEvent event) {

    }

    @FXML
    void textFieldAnswerBAction(ActionEvent event) {

    }

    @FXML
    void textFieldAnswerBisLetter(KeyEvent event) {

    }

    @FXML
    void textFieldAnswerCAction(ActionEvent event) {

    }

    @FXML
    void textFieldAnswerCisLetter(KeyEvent event) {

    }

    @FXML
    void textFieldAnswerDAction(ActionEvent event) {

    }

    @FXML
    void textFieldAnswerDisLetter(KeyEvent event) {

    }

    @FXML
    void textFieldQuestionIsLetter(KeyEvent event) {

    }

    @FXML
    void textFieldQuestionNameAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
