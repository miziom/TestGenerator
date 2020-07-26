package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.PopUpManager;
import com.mizio.manager.ViewManager;
import com.mizio.model.Subject;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.repository.RepositoryListViewer;
import com.mizio.repository.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {

    private RepositoryService repositoryService = new RepositoryService();

    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();

    @FXML
    private JFXButton buttonBack;

    @FXML
    private TableView<Subject> tableView;

    @FXML
    private TableColumn<Subject, String> columnSubjectName;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemDelete;

    @FXML
    private JFXTextField textFieldSubjectName;

    @FXML
    private JFXButton buttonAddSubject;

    @FXML
    void contextMenuAction(ActionEvent event) {

    }

    @FXML
    void menuItemDeleteAction(ActionEvent event) {
        if (PopUpManager.deleteItems(tableView.getSelectionModel().getSelectedItems())) {
            for (Subject subject:tableView.getSelectionModel().getSelectedItems()) {
                repositoryService.deleteObject(subject.getClass(), subject.getSubjectID());
            }
            repositoryListViewer.saveOrUpdateList();
            tableViewRefresh();
        }
    }

    @FXML
    void buttonAddSubjectAction(ActionEvent event) {
        addSubject();
        tableViewRefresh();
    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void isLetterAction(KeyEvent event) {
        checkButton();
    }

    @FXML
    void textFieldSubjectNameAction(ActionEvent event) {
        addSubject();
        tableViewRefresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonAddSubject.setDisable(true);
        tableViewRefresh();
    }

    private void addSubject() {
        Subject subject = Subject.builder()
                .subjectName(textFieldSubjectName.getText().trim())
                .build();
        repositoryService.saveOrUpdateObject(subject);
        repositoryListViewer.saveOrUpdateList();
        textFieldSubjectName.clear();
        buttonAddSubject.setDisable(true);
    }

    private void checkButton() {
        if (textFieldSubjectName.getText().isBlank()) {
            buttonAddSubject.setDisable(true);
        } else {
            buttonAddSubject.setDisable(false);
        }
    }

    private void tableViewRefresh() {
        tableView.getItems().clear();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tableView.getItems().setAll(repositoryListViewer.getSubjectList());
    }

}
