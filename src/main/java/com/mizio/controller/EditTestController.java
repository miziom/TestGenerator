package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mizio.concurrency.TableViewTestThread;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import com.mizio.service.RepositoryListViewerService;
import com.mizio.service.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTestController implements Initializable {

    RepositoryService repositoryService = new RepositoryService();
    RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();
    private static Test test;
    private static TableViewTestThread thread;

    @FXML
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private JFXTextField textFieldTestName;

    @FXML
    private JFXButton buttonEditTest;

    @FXML
    private JFXButton buttonClose;

    @FXML
    void buttonCloseAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void buttonEditTestAction(ActionEvent event) {
        updateObject();
        closeStage();
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {
        checkChangeButton();
    }

    @FXML
    void textFieldIsLetterAction(KeyEvent event) {
        checkChangeButton();
    }

    @FXML
    void textFieldTestNameAction(ActionEvent event) {
        updateObject();
        closeStage();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxSubject.getItems().setAll(repositoryListViewer.getSubjectList());
        comboBoxSubject.getSelectionModel().select(test.getSubject());
        textFieldTestName.setText(test.getTestName());
        checkChangeButton();
    }

    public void setSubjectAndTest(Test myTest) {
        test = myTest;
    }

    public void setThread(TableViewTestThread tableViewThread) {
        thread = tableViewThread;
    }

    public static void clean() {
        thread.notifyGuard();
        test = null;
        thread = null;
    }

    private void closeStage() {
        clean();
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    private void updateObject() {
        test.setTestName(textFieldTestName.getText().trim());
        test.setSubject(comboBoxSubject.getSelectionModel().getSelectedItem());
        repositoryService.saveOrUpdateObject(test);
        repositoryListViewer.saveOrUpdateList();
    }

    private void checkChangeButton() {
        if (!textFieldTestName.getText().trim().equals(test.getTestName())
        || comboBoxSubject.getSelectionModel().getSelectedItem() != test.getSubject()) {
            buttonEditTest.setDisable(false);
        } else {
            buttonEditTest.setDisable(true);
        }
    }
}
