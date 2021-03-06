package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.mizio.concurrency.TableViewTestThread;
import com.mizio.manager.PopUpManager;
import com.mizio.manager.ViewManager;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.service.RepositoryListViewerService;
import com.mizio.service.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTestController implements Initializable {

    private RepositoryService repositoryService = new RepositoryService();

    private RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();

    private EditTestController editTestController = new EditTestController();

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private TableView<Test> tableView;

    @FXML
    private TableColumn<Test, String> columnTestName;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemEdit;

    @FXML
    private MenuItem menuItemDelete;

    @FXML
    private JFXTextField textFieldTestName;

    @FXML
    private JFXButton buttonAddTest;

    @FXML
    void buttonAddTestAction(ActionEvent event) {
        addTest();
        tableViewRefresh();
    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {
        tableViewRefresh();
    }

    @FXML
    void isLetterAction(KeyEvent event) {
        checkButton();
    }

    @FXML
    void menuItemEditAction(ActionEvent event) {
        TableViewTestThread tableViewTestThread = new TableViewTestThread(
                tableView,
                columnTestName,
                comboBoxSubject.getSelectionModel().getSelectedItem().getSubjectID()
        );
        new Thread(tableViewTestThread).start();
        editTestController.setThread(tableViewTestThread);
        editTestController.setSubjectAndTest(tableView.getSelectionModel().getSelectedItem());
        ViewManager.loadNewWindow(PathPattern.EDIT_TEST_VIEW, TitlePattern.EDIT_TEST_VIEW, Test.class);
    }

    @FXML
    void menuItemDeleteAction(ActionEvent event) {
        if (PopUpManager.deleteItems(tableView.getSelectionModel().getSelectedItems())) {
            for (Test test:tableView.getSelectionModel().getSelectedItems()) {
                repositoryService.deleteObject(test.getClass(), test.getTestID());
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

    @FXML
    void textFieldTestNameAction(ActionEvent event) {
        addTest();
        tableViewRefresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonAddTest.setDisable(true);
        comboBoxSubject.getItems().setAll(repositoryListViewer.getSubjectList());
        if (!repositoryListViewer.getSubjectList().isEmpty()) {
            comboBoxSubject.getSelectionModel().selectFirst();
            tableViewRefresh();
        }
    }

    private void checkButton() {
        if (textFieldTestName.getText().isBlank()) {
            buttonAddTest.setDisable(true);
        } else {
            buttonAddTest.setDisable(false);
        }
    }

    private void tableViewRefresh() {
        tableView.getItems().clear();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnTestName.setCellValueFactory(new PropertyValueFactory<>("testName"));
        tableView.getItems().setAll(repositoryListViewer.getSubject(
                comboBoxSubject.getSelectionModel()
                        .getSelectedItem()
                        .getSubjectID())
                .getTests());
    }

    private void addTest() {
        Test test = Test.builder()
                .subject(comboBoxSubject.getSelectionModel().getSelectedItem())
                .testName(textFieldTestName.getText().trim())
                .build();
        repositoryService.saveOrUpdateObject(test);
        repositoryListViewer.saveOrUpdateList();
        textFieldTestName.clear();
        buttonAddTest.setDisable(true);
    }
}