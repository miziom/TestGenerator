package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ImageManager;
import com.mizio.manager.PopUpManager;
import com.mizio.manager.ViewManager;
import com.mizio.model.*;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.repository.RepositoryListViewer;
import com.mizio.service.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AddQuestionController implements Initializable {

    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();

    private RepositoryService repositoryService = new RepositoryService();

    private static Image image = null;

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private JFXComboBox<Test> comboBoxTest;

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
        File file = PopUpManager.directoryChooser(event);
        if (file != null) {
            byte[] byteImage = ImageManager.getByteFromFile(file);
            image = Image.builder()
                    .imageName(file.getName())
                    .image(byteImage)
                    .build();
            buttonAddImg.setText(file.getName());
            imgCheckmark.setVisible(true);
        } else {
            image = null;
            buttonAddImg.setText(TitlePattern.NO_IMG);
            imgCheckmark.setVisible(false);
        }
    }

    @FXML
    void buttonAddQuestionAction(ActionEvent event) {
        addQuestion();
    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {
        comboBoxTestRefresh();
        checkAddButton();
    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {
        checkAddButton();
    }

    @FXML
    void imgCheckMarkMouseClicked(MouseEvent event) {
        image = null;
        buttonAddImg.setText(TitlePattern.NO_IMG);
        imgCheckmark.setVisible(false);
    }

    @FXML
    void radioABAction(ActionEvent event) {
        radioAB.setSelected(true);
        clearTextField();
        checkRadioABCD();
        checkAddButton();
    }

    @FXML
    void radioABCAction(ActionEvent event) {
        radioABC.setSelected(true);
        clearTextField();
        checkRadioABCD();
        checkAddButton();

    }

    @FXML
    void radioABCDAction(ActionEvent event) {
        radioABCD.setSelected(true);
        checkRadioABCD();
        checkAddButton();
    }

    @FXML
    void textFieldAnswerAAction(ActionEvent event) {
        if (checkAddButton()) {
            addQuestion();
        }
    }

    @FXML
    void textFieldAnswerAisLetter(KeyEvent event) {
        checkAddButton();
    }

    @FXML
    void textFieldAnswerBAction(ActionEvent event) {
        if (checkAddButton()) {
            addQuestion();
        }
    }

    @FXML
    void textFieldAnswerBisLetter(KeyEvent event) {
        checkAddButton();
    }

    @FXML
    void textFieldAnswerCAction(ActionEvent event) {
        if (checkAddButton()) {
            addQuestion();
        }
    }

    @FXML
    void textFieldAnswerCisLetter(KeyEvent event) {
        checkAddButton();
    }

    @FXML
    void textFieldAnswerDAction(ActionEvent event) {
        if (checkAddButton()) {
            addQuestion();
        }
    }

    @FXML
    void textFieldAnswerDisLetter(KeyEvent event) {
        checkAddButton();
    }

    @FXML
    void textFieldQuestionIsLetter(KeyEvent event) {
        checkAddButton();
    }

    @FXML
    void textFieldQuestionNameAction(ActionEvent event) {
        if (checkAddButton()) {
            addQuestion();
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
        }
        buttonAddImg.setText(TitlePattern.NO_IMG);
        imgCheckmark.setVisible(false);
        buttonAddQuestion.setDisable(true);
        checkRadioABCD();
    }

    private void checkRadioABCD() {
        if (radioAB.isSelected()) {
            textFieldAnswerC.setDisable(true);
            textFieldAnswerD.setDisable(true);
            radioCorrectC.setDisable(true);
            radioCorrectD.setDisable(true);
        } else if (radioABC.isSelected()) {
            textFieldAnswerC.setDisable(false);
            textFieldAnswerD.setDisable(true);
            radioCorrectC.setDisable(false);
            radioCorrectD.setDisable(true);
        } else if (radioABCD.isSelected()) {
            textFieldAnswerC.setDisable(false);
            textFieldAnswerD.setDisable(false);
            radioCorrectC.setDisable(false);
            radioCorrectD.setDisable(false);
        }
        radioCorrectA.setSelected(true);
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

    private boolean checkAddButton() {
        if (comboBoxSubject.getSelectionModel().getSelectedItem() != null
        && comboBoxTest.getSelectionModel().getSelectedItem() != null
        && !textFieldQuestionName.getText().isBlank()
        && !textFieldAnswerA.getText().isBlank()
        && !textFieldAnswerB.getText().isBlank()) {
            if (radioAB.isSelected()) {
                buttonAddQuestion.setDisable(false);
                return true;
            }
            else if (radioABC.isSelected()
                    && !textFieldAnswerC.getText().isBlank()) {
                        buttonAddQuestion.setDisable(false);
                        return true;
            }
            else if (radioABCD.isSelected()
                    && !textFieldAnswerC.getText().isBlank()
                    && !textFieldAnswerD.getText().isBlank()) {
                        buttonAddQuestion.setDisable(false);
                        return true;
            } else {
                buttonAddQuestion.setDisable(true);
                return false;
            }
        } else {
            buttonAddQuestion.setDisable(true);
            return false;
        }
    }

    private void clearTextField() {
        if (radioAB.isSelected()) {
            textFieldAnswerC.clear();
            textFieldAnswerD.clear();
        } else if (radioABC.isSelected()) {
            textFieldAnswerD.clear();
        }
    }

    private void addQuestion() {
        Subject selectedSubject = comboBoxSubject.getSelectionModel().getSelectedItem();
        Test selectedTest = comboBoxTest.getSelectionModel().getSelectedItem();
        Question question = Question.builder()
                .subject(selectedSubject)
                .test(selectedTest)
                .questionName(textFieldQuestionName.getText().trim())
                .build();
        if (image != null) {
            image.setSubject(selectedSubject);
            image.setTest(selectedTest);
            image.setQuestion(question);
            question.setImage(image);
        }
        AnswersContent answersContent = AnswersContent.builder()
                .subject(selectedSubject)
                .test(selectedTest)
                .question(question)
                .answerA(textFieldAnswerA.getText().trim())
                .answerB(textFieldAnswerB.getText().trim())
                .build();
        if (radioABC.isSelected()) {
            answersContent.setAnswerC(textFieldAnswerC.getText().trim());
        }
        else if (radioABCD.isSelected()) {
            answersContent.setAnswerC(textFieldAnswerC.getText().trim());
            answersContent.setAnswerD(textFieldAnswerD.getText().trim());
        }
        question.setAnswersContent(answersContent);
        question.setAnswerCorrect(getAnswerCorrect());
        question.setQuestionType(getQuestionType());
        repositoryService.saveOrUpdateObject(question);
        repositoryListViewer.saveOrUpdateList();
        image = null;
        resetRadioAndFields();
    }

    private AnswerCorrect getAnswerCorrect() {
        if (radioCorrectA.isSelected()) {
            return AnswerCorrect.A;
        } else if (radioCorrectB.isSelected()) {
            return AnswerCorrect.B;
        } else if (radioCorrectC.isSelected()) {
            return AnswerCorrect.C;
        } else if (radioCorrectD.isSelected()) {
            return AnswerCorrect.D;
        } else {
            return null;
        }
    }

    private QuestionType getQuestionType() {
        if (radioAB.isSelected()) {
            return QuestionType.AB;
        } else if (radioABC.isSelected()) {
            return QuestionType.ABC;
        } else if (radioABCD.isSelected()) {
            return QuestionType.ABCD;
        } else {
            return null;
        }
    }

    private void resetRadioAndFields() {
        textFieldQuestionName.clear();
        textFieldAnswerA.clear();
        textFieldAnswerB.clear();
        textFieldAnswerC.clear();
        textFieldAnswerD.clear();
        radioAB.setSelected(true);
        radioCorrectA.setSelected(true);
        checkRadioABCD();
        checkAddButton();
    }
}