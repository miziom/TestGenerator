package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.concurrency.TableViewSubjectThread;
import com.mizio.model.Subject;
import com.mizio.service.RepositoryListViewerService;
import com.mizio.service.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSubjectController implements Initializable {

    RepositoryService repositoryService = new RepositoryService();
    RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();
    private static int subjectID;
    private static Subject subject;
    private static TableViewSubjectThread thread;

    @FXML
    private JFXTextField textFieldSubjectName;

    @FXML
    private JFXButton buttonChangeSubjectName;

    @FXML
    private JFXButton buttonClose;

    @FXML
    void buttonChangeSubjectNameAction(ActionEvent event) {
        updateObject();
        closeStage();
    }

    @FXML
    void buttonCloseAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void textFieldSubjectNameAction(ActionEvent event) {
        updateObject();
        closeStage();
    }

    @FXML
    void textFieldSubjectNameIsLetter(KeyEvent event) {
        checkChangeButton();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        subject = repositoryService.getObject(Subject.class, subjectID);
        textFieldSubjectName.setText(subject.getSubjectName());
        checkChangeButton();
    }

    public void setSubjectID(int ID) {
        subjectID = ID;
    }

    public void setThread(TableViewSubjectThread tableViewThread) {
        thread = tableViewThread;
    }

    public static void clean() {
        thread.notifyGuard();
        subjectID = 0;
        subject = null;
        thread = null;
    }

    private void checkChangeButton() {
        if (textFieldSubjectName.getText().trim().equals(subject.getSubjectName())) {
            buttonChangeSubjectName.setDisable(true);
        } else {
            buttonChangeSubjectName.setDisable(false);
        }
    }

    private void updateObject() {
        subject.setSubjectName(textFieldSubjectName.getText().trim());
        repositoryService.saveOrUpdateObject(subject);
        repositoryListViewer.saveOrUpdateList();
    }

    private void closeStage() {
        clean();
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }
}