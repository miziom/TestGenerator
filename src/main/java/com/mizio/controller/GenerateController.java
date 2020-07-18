package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.mizio.manager.ViewManager;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class GenerateController implements Initializable {
    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<?> comboBoxGroupNumber;

    @FXML
    private JFXComboBox<?> comboBoxGroupFirst;

    @FXML
    private JFXComboBox<?> comboBoxGroupSecond;

    @FXML
    private JFXComboBox<?> comboBoxGroupThird;

    @FXML
    private JFXComboBox<?> comboBoxSubject;

    @FXML
    private JFXComboBox<?> comboBoxTest;

    @FXML
    private JFXRadioButton radioButtonTableView;

    @FXML
    private ToggleGroup radioDraftType;

    @FXML
    private JFXComboBox<?> comboBoxQuestionsNumber;

    @FXML
    private JFXButton buttonGenerate;

    @FXML
    private Label labelProgressBar;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private JFXRadioButton radioButtonComboBox;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> columnQuestionName;

    @FXML
    private TableColumn<?, ?> columnImage;

    @FXML
    private TableColumn<?, ?> columnType;

    @FXML
    private TableColumn<?, ?> columnSelect;

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void buttonGenerateAction(ActionEvent event) {

    }

    @FXML
    void comboBoxGroupFirstAction(ActionEvent event) {

    }

    @FXML
    void comboBoxGroupNumberAction(ActionEvent event) {

    }

    @FXML
    void comboBoxGroupSecondAction(ActionEvent event) {

    }

    @FXML
    void comboBoxGroupThirdAction(ActionEvent event) {

    }

    @FXML
    void comboBoxQuestionsNumberAction(ActionEvent event) {

    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {

    }

    @FXML
    void mouseClickDraft(MouseEvent event) {

    }

    @FXML
    void radioButtonComboBoxAction(ActionEvent event) {

    }

    @FXML
    void radioButtonTableViewAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
