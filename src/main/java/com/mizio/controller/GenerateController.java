package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXProgressBar;
import com.jfoenix.controls.JFXRadioButton;
import com.mizio.dto.QuestionDTO;
import com.mizio.manager.PopUpManager;
import com.mizio.manager.RangeManager;
import com.mizio.manager.ViewManager;
import com.mizio.mapper.Mapper;
import com.mizio.model.GroupDetail;
import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import com.mizio.pattern.LabelPattern;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.repository.RepositoryListViewer;
import com.mizio.service.FileOutService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GenerateController implements Initializable {

    RepositoryListViewer repositoryListViewer = new RepositoryListViewer();
    Mapper mapper = new Mapper();

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<Integer> comboBoxGroupNumber;

    @FXML
    private JFXComboBox<GroupDetail> comboBoxGroupFirst;

    @FXML
    private JFXComboBox<GroupDetail> comboBoxGroupSecond;

    @FXML
    private JFXComboBox<GroupDetail> comboBoxGroupThird;

    @FXML
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private JFXComboBox<Test> comboBoxTest;

    @FXML
    private JFXRadioButton radioButtonTableView;

    @FXML
    private ToggleGroup radioDraftType;

    @FXML
    private JFXComboBox<Integer> comboBoxQuestionsNumber;

    @FXML
    private JFXButton buttonGenerate;

    @FXML
    private Label labelProgressBar;

    @FXML
    private JFXProgressBar progressBar;

    @FXML
    private JFXRadioButton radioButtonComboBox;

    @FXML
    private TableView<QuestionDTO> tableView;

    @FXML
    private TableColumn<QuestionDTO, String> columnQuestionName;

    @FXML
    private TableColumn<QuestionDTO, String> columnImage;

    @FXML
    private TableColumn<QuestionDTO, String> columnType;

    @FXML
    private TableColumn<QuestionDTO, CheckBox> columnSelect;

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void buttonGenerateAction(ActionEvent event) {
        File saveFile = PopUpManager.saveFile(event);
        FileOutService fileOutService = new FileOutService(
                saveFile,
                getGroupDetailList(),
                comboBoxGroupNumber.getSelectionModel().getSelectedItem());
        if (radioButtonTableView.isSelected()) {
            fileOutService.generateFilesForSelectedQuestions(tableView.getItems());
        } else if (radioButtonComboBox.isSelected()) {
            fileOutService.generateFilesForDeterminedNumberOfQuestions(
                    tableView.getItems(),
                    comboBoxQuestionsNumber.getSelectionModel().getSelectedItem()
            );
        }
    }

    @FXML
    void comboBoxGroupFirstAction(ActionEvent event) {

    }

    @FXML
    void comboBoxGroupNumberAction(ActionEvent event) {
        manageComboBoxGroup();
        fillComboBoxQuestionNumber();
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
        comboBoxTestRefresh();
        tableViewRefresh();
        showCheckedQuestion();
        fillComboBoxQuestionNumber();
    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {
        tableViewRefresh();
        showCheckedQuestion();
        fillComboBoxQuestionNumber();
    }

    @FXML
    void mouseClickDraft(MouseEvent event) {
        showCheckedQuestion();
    }

    @FXML
    void radioButtonComboBoxAction(ActionEvent event) {
        checkRadioButton();
    }

    @FXML
    void radioButtonTableViewAction(ActionEvent event) {
        checkRadioButton();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (!repositoryListViewer.getSubjectList().isEmpty()) {
            comboBoxSubject.getItems().setAll(repositoryListViewer.getSubjectList());
            comboBoxSubject.getSelectionModel().selectFirst();
            comboBoxTestRefresh();
            tableViewRefresh();
        }
        radioButtonTableView.setSelected(true);
        checkRadioButton();
        comboBoxGroupNumber.getItems().setAll(RangeManager.fillIntegerList(1, 3));
        comboBoxGroupNumber.getSelectionModel().selectFirst();
        fillComboBoxGroup();
        manageComboBoxGroup();
        showCheckedQuestion();
        fillComboBoxQuestionNumber();
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

    private void tableViewRefresh() {
        tableView.getItems().clear();
        columnQuestionName.setCellValueFactory(new PropertyValueFactory<>("questionName"));
        columnImage.setCellValueFactory(data -> {
            StringProperty stringProperty = new SimpleStringProperty();
            if (data.getValue().getImageDTO() == null) {
                stringProperty.setValue(TitlePattern.NO_CONTENT);
            } else {
                stringProperty.setValue(data.getValue().getImageDTO().getImageName());
            }
            return stringProperty;
        });
        columnType.setCellValueFactory(new PropertyValueFactory<>("questionType"));
        columnSelect.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        if (!comboBoxTest.getItems().isEmpty()) {
            if (!comboBoxTest.getSelectionModel().getSelectedItem().getQuestions().isEmpty()) {
                List<Question> questions = repositoryListViewer.getTest(
                        comboBoxSubject.getSelectionModel().getSelectedItem().getSubjectID(),
                        comboBoxTest.getSelectionModel().getSelectedItem().getTestID())
                        .getQuestions();
                List<QuestionDTO> questionDTOList = mapper.mapQuestionListToQuestionDTOList(questions);
                tableView.getItems().setAll(questionDTOList);
            }
        }
    }

    private void checkRadioButton() {
        if (radioButtonComboBox.isSelected()) {
            tableView.setDisable(true);
            comboBoxQuestionsNumber.setDisable(false);
        } else if (radioButtonTableView.isSelected()) {
            tableView.setDisable(false);
            comboBoxQuestionsNumber.setDisable(true);
        }
    }

    private void manageComboBoxGroup() {
        comboBoxGroupFirst.getSelectionModel().selectFirst();
        switch (comboBoxGroupNumber.getSelectionModel().getSelectedItem()) {
            case 1: {
                comboBoxGroupSecond.setDisable(true);
                comboBoxGroupThird.setDisable(true);
            } break;
            case 2: {
                comboBoxGroupSecond.setDisable(false);
                comboBoxGroupThird.setDisable(true);
                comboBoxGroupSecond.getSelectionModel().select(1);
            } break;
            case 3: {
                comboBoxGroupSecond.setDisable(false);
                comboBoxGroupThird.setDisable(false);
                comboBoxGroupSecond.getSelectionModel().select(1);
                comboBoxGroupThird.getSelectionModel().select(2);
            } break;
        }
    }

    private void fillComboBoxGroup() {
        comboBoxGroupFirst.getItems().setAll(repositoryListViewer.getGroupDetailList());
        comboBoxGroupSecond.getItems().setAll(repositoryListViewer.getGroupDetailList());
        comboBoxGroupThird.getItems().setAll(repositoryListViewer.getGroupDetailList());
    }

    private void fillComboBoxQuestionNumber() {
        if (tableView.getItems() != null) {
            if (tableView.getItems().size() >= comboBoxGroupNumber.getSelectionModel().getSelectedItem()) {
                comboBoxQuestionsNumber.getItems().setAll(RangeManager.fillIntegerList(
                        comboBoxGroupNumber.getSelectionModel().getSelectedItem(),
                        RangeManager.countMaxQuestionNumber(
                                tableView.getItems(),
                                comboBoxGroupNumber.getSelectionModel().getSelectedItem()
                        )
                ));
            } else if (comboBoxQuestionsNumber.getItems() != null) {
                comboBoxQuestionsNumber.getItems().clear();
            }
        } else {
            if (comboBoxQuestionsNumber.getItems() != null) {
                comboBoxQuestionsNumber.getItems().clear();
            }
        }
    }

    private void showCheckedQuestion() {
        if (tableView.getItems().isEmpty()) {
            columnSelect.setText(String.format(LabelPattern.QUESTIONS_CHECKED, 0, 0));
        } else {
            columnSelect.setText(String.format(LabelPattern.QUESTIONS_CHECKED,
                                                tableView.getItems().stream()
                                                        .filter(questionDTO -> questionDTO.getCheckBox().isSelected())
                                                        .count(),
                                                tableView.getItems().size()));
        }
    }

    private List<GroupDetail> getGroupDetailList() {
        List<GroupDetail> groupDetails = new ArrayList<>();
        List<GroupDetail> repo = repositoryListViewer.getGroupDetailList();
        Optional<GroupDetail> g1 = repo.stream().filter(groupDetail -> groupDetail.getGroupID() == comboBoxGroupFirst.getSelectionModel().getSelectedItem().getGroupID()).findFirst();
        groupDetails.add(g1.get());
        switch (comboBoxGroupNumber.getSelectionModel().getSelectedItem()) {
            case 2: {
                Optional<GroupDetail> g2 = repo.stream().filter(groupDetail -> groupDetail.getGroupID() == comboBoxGroupSecond.getSelectionModel().getSelectedItem().getGroupID()).findFirst();
                groupDetails.add(g2.get());
            }break;
            case 3: {
                Optional<GroupDetail> g2 = repo.stream().filter(groupDetail -> groupDetail.getGroupID() == comboBoxGroupSecond.getSelectionModel().getSelectedItem().getGroupID()).findFirst();
                Optional<GroupDetail> g3 = repo.stream().filter(groupDetail -> groupDetail.getGroupID() == comboBoxGroupThird.getSelectionModel().getSelectedItem().getGroupID()).findFirst();
                groupDetails.add(g2.get());
                groupDetails.add(g3.get());
            }break;
        }
        return groupDetails;
    }
}