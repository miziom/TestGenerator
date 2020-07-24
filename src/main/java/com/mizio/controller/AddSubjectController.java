package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.model.Subject;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.repository.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddSubjectController implements Initializable {

    private RepositoryService repositoryService = new RepositoryService();

    @FXML
    private JFXButton buttonBack;

    @FXML
    private TableView<Subject> tableView;

    @FXML
    private TableColumn<Subject, String> columnSubjectName;

    @FXML
    private JFXTextField textFieldSubjectName;

    @FXML
    private JFXButton buttonAddSubject;

    @FXML
    void buttonAddSubjectAction(ActionEvent event) {
        addSubject();
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
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttonAddSubject.setDisable(true);
        List<Subject> subjects = repositoryService.getSubjectsList();
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tableView.getItems().setAll(subjects);
    }

    private void addSubject() {
        Subject subject = Subject.builder()
                .subjectName(textFieldSubjectName.getText().trim())
                .build();
        repositoryService.saveOrUpdateObject(subject);
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

}
