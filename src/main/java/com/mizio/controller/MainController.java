package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mizio.model.Question;
import com.mizio.model.Test;
import com.mizio.repository.RepositoryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    RepositoryService repositoryService = new RepositoryService();

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
    private JFXComboBox<?> comboBoxSubject;

    @FXML
    private JFXComboBox<?> comboBoxTest;

    @FXML
    private Label labelQuestionCounter;

    @FXML
    private TableView<Question> tableVIew;

    @FXML
    private TableColumn<Question, String> columnQuestionName;

    @FXML
    private TableColumn<Question, String> columnImage;

    @FXML
    private TableColumn<Question, String> columnQuestionType;

    @FXML
    private TableColumn<Question, String> columnA;

    @FXML
    private TableColumn<Question, String> columnB;

    @FXML
    private TableColumn<Question, String> columnC;

    @FXML
    private TableColumn<Question, String> columnD;

    @FXML
    private TableColumn<Question, String> columnCorrectAnswer;

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

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {

    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Test test = repositoryService.getObject(Test.class, 3);
        List<Question> questions = test.getQuestions();
        tableVIew.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnQuestionName.setCellValueFactory(new PropertyValueFactory<>("questionName"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnQuestionType.setCellValueFactory(new PropertyValueFactory<>("questionType"));
        columnA.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(String.valueOf(data.getValue().getAnswersContent().getAnswerA()));
            return stringProperty;
        });
        columnB.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(String.valueOf(data.getValue().getAnswersContent().getAnswerB()));

            return stringProperty;
        });
        columnC.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(String.valueOf(data.getValue().getAnswersContent().getAnswerC()));
            if(data.getValue().getAnswersContent().getAnswerC() == null) {
                stringProperty.setValue("-");
            }
            return stringProperty;
        });
        columnD.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(String.valueOf(data.getValue().getAnswersContent().getAnswerD()));
            if(data.getValue().getAnswersContent().getAnswerD() == null) {
                stringProperty.setValue("-");
            }
            return stringProperty;
        });
        columnCorrectAnswer.setCellValueFactory(new PropertyValueFactory<>("answerCorrect"));
        tableVIew.getItems().setAll(questions);

    }
}
