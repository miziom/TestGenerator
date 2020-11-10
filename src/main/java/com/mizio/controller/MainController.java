package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.mizio.concurrency.TableViewQuestionThread;
import com.mizio.manager.PopUpManager;
import com.mizio.manager.ViewManager;
import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.service.RepositoryListViewerService;
import com.mizio.service.RepositoryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    RepositoryService repositoryService = new RepositoryService();

    RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();

    EditQuestionController editQuestionController = new EditQuestionController();

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
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private JFXComboBox<Test> comboBoxTest;

    @FXML
    private Label labelQuestionCounter;

    @FXML
    private TableView<Question> tableView;

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
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemEdit;

    @FXML
    private MenuItem menuItemDelete;

    @FXML
    void buttonAddFileAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.ADD_FILE_VIEW, TitlePattern.ADD_FILE_VIEW, event);
    }

    @FXML
    void buttonAddQuestionAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.ADD_QUESTION_VIEW, TitlePattern.ADD_QUESTION_VIEW, event);
    }

    @FXML
    void buttonAddSubjectAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.ADD_SUBJECT_VIEW, TitlePattern.ADD_SUBJECT_VIEW, event);
    }

    @FXML
    void buttonAddTestAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.ADD_TEST_VIEW, TitlePattern.ADD_TEST_VIEW, event);
    }

    @FXML
    void buttonGenerateAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.GENERATE_VIEW, TitlePattern.GENERATE_VIEW, event);
    }

    @FXML
    void buttonSettingsAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.SETTINGS_VIEW, TitlePattern.SETTINGS_VIEW, event);
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {
        comboBoxTestRefresh();
        tableViewRefresh();
    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {
        tableViewRefresh();
    }

    @FXML
    void menuItemEditAction(ActionEvent event) {
        TableViewQuestionThread tableViewQuestionThread = new TableViewQuestionThread(
                tableView.getSelectionModel().getSelectedItem(),
                tableView,
                columnQuestionName,
                columnImage,
                columnQuestionType,
                columnA,
                columnB,
                columnC,
                columnD,
                columnCorrectAnswer,
                labelQuestionCounter);
        new Thread(tableViewQuestionThread).start();
        editQuestionController.setItems(tableView.getSelectionModel().getSelectedItem(), tableViewQuestionThread);
        ViewManager.loadNewWindow(PathPattern.EDIT_QUESTION_VIEW, TitlePattern.EDIT_QUESTION_VIEW, Question.class);
    }

    @FXML
    void menuItemDeleteAction(ActionEvent event) {
        if (PopUpManager.deleteItems(tableView.getSelectionModel().getSelectedItems())) {
            for (Question question:tableView.getSelectionModel().getSelectedItems()) {
                repositoryService.deleteObject(question.getClass(), question.getQuestionID());
            }
            repositoryListViewer.saveOrUpdateList();
            tableViewRefresh();
        }
    }

    @FXML
    void tableViewContextMenuAction(ContextMenuEvent event) {
        if (tableView.getSelectionModel().getSelectedItems().size() > 1) {
            menuItemEdit.setDisable(true);
        } else {
            menuItemEdit.setDisable(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (repositoryListViewer.getSubjectList() == null) {
            repositoryListViewer.saveOrUpdateList();
        }
        if (!repositoryListViewer.getSubjectList().isEmpty()) {
            comboBoxSubject.getItems().setAll(repositoryListViewer.getSubjectList());
            comboBoxSubject.getSelectionModel().selectFirst();
            comboBoxTestRefresh();
            tableViewRefresh();
        }
    }

    private void comboBoxTestRefresh() {
        if (!comboBoxSubject.getSelectionModel().getSelectedItem().getTests().isEmpty()) {
            comboBoxTest.getItems().setAll(comboBoxSubject.getSelectionModel().getSelectedItem().getTests());
            comboBoxTest.getSelectionModel().selectFirst();
        }
        else {
            comboBoxTest.getItems().clear();
        }
    }

    private void setLabelQuestionCounter() {
        if(tableView.getItems().isEmpty()) {
            labelQuestionCounter.setText(String.format(TitlePattern.COUNTER_QUESTION, 0));
        } else {
            labelQuestionCounter.setText(String.format(TitlePattern.COUNTER_QUESTION, tableView.getItems().size()));
        }
    }

    private void tableViewRefresh() {
        tableView.getItems().clear();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnQuestionName.setCellValueFactory(new PropertyValueFactory<>("questionName"));
        columnImage.setCellValueFactory(new PropertyValueFactory<>("image"));
        columnImage.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            if (data.getValue().getImage() == null) {
                stringProperty.setValue(TitlePattern.NO_CONTENT);
            } else {
                stringProperty.setValue(data.getValue().getImage().getImageName());
            }
            return stringProperty;
        });
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
                stringProperty.setValue(TitlePattern.NO_CONTENT);
            }
            return stringProperty;
        });
        columnD.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            stringProperty.setValue(String.valueOf(data.getValue().getAnswersContent().getAnswerD()));
            if(data.getValue().getAnswersContent().getAnswerD() == null) {
                stringProperty.setValue(TitlePattern.NO_CONTENT);
            }
            return stringProperty;
        });
        columnCorrectAnswer.setCellValueFactory(new PropertyValueFactory<>("answerCorrect"));
        if (!comboBoxTest.getItems().isEmpty()) {
            if (!comboBoxTest.getSelectionModel().getSelectedItem().getQuestions().isEmpty()) {
                tableView.getItems().setAll(repositoryListViewer.getTest(
                        comboBoxSubject.getSelectionModel().getSelectedItem().getSubjectID(),
                        comboBoxTest.getSelectionModel().getSelectedItem().getTestID())
                        .getQuestions());
            }
        }
        setLabelQuestionCounter();
    }
}