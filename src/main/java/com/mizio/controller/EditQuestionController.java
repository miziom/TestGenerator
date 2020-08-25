package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.mizio.concurrency.TableViewQuestionThread;
import com.mizio.manager.ImageManager;
import com.mizio.manager.PopUpManager;
import com.mizio.model.*;
import com.mizio.pattern.LabelPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.repository.RepositoryListViewer;
import com.mizio.repository.RepositoryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class EditQuestionController implements Initializable {

    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();
    private RepositoryService repositoryService = new RepositoryService();
    private static Image image = null;
    private static Question question = null;
    private static TableViewQuestionThread thread = null;

    @FXML
    private JFXComboBox<Subject> comboBoxSubject;

    @FXML
    private JFXComboBox<Test> comboBoxTest;

    @FXML
    private JFXComboBox<Subject> comboBoxSubjectChoosed;

    @FXML
    private JFXComboBox<Test> comboBoxTestChoosed;

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
    private ImageView imgCheckMark;

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
    private JFXButton buttonEditQuestion;

    @FXML
    private JFXButton buttonClose;

    @FXML
    void buttonAddImgAction(ActionEvent event) {
        File file = PopUpManager.directoryChooser(event);
        if (file != null && question.getImage() == null) {
            byte[] byteImage = ImageManager.getByteFromFile(file);
            image = Image.builder()
                    .imageName(file.getName())
                    .image(byteImage)
                    .build();
            buttonAddImg.setText(file.getName());
            imgCheckMark.setVisible(true);
        } else if (file != null && question.getImage() != null) {
            image = question.getImage();
            byte[] byteImage = ImageManager.getByteFromFile(file);
            image.setImageName(file.getName());
            image.setImage(byteImage);
            buttonAddImg.setText(file.getName());
            imgCheckMark.setVisible(true);
        }
        else {
            image = null;
            buttonAddImg.setText(TitlePattern.NO_IMG);
            imgCheckMark.setVisible(false);
        }
    }

    @FXML
    void buttonCloseAction(ActionEvent event) {
        closeStage();
    }

    @FXML
    void buttonEditQuestionAction(ActionEvent event) {
        editQuestion();
        closeStage();
    }

    @FXML
    void comboBoxSubjectAction(ActionEvent event) {
        comboBoxTestRefresh();
        checkEditButton();
    }

    @FXML
    void comboBoxTestAction(ActionEvent event) {
        checkEditButton();
    }

    @FXML
    void imgCheckMarkMouseClicked(MouseEvent event) {
        image = null;
        imgCheckMark.setVisible(false);
        buttonAddImg.setText(TitlePattern.NO_IMG);
    }

    @FXML
    void radioABAction(ActionEvent event) {
        radioAB.setSelected(true);
        clearTextField();
        checkRadioABCD();
        checkEditButton();
    }

    @FXML
    void radioABCAction(ActionEvent event) {
        radioABC.setSelected(true);
        clearTextField();
        checkRadioABCD();
        checkEditButton();
    }

    @FXML
    void radioABCDAction(ActionEvent event) {
        radioABCD.setSelected(true);
        clearTextField();
        checkRadioABCD();
        checkEditButton();
    }

    @FXML
    void textFieldAnswerAAction(ActionEvent event) {
        if (checkEditButton()) {
            editQuestion();
            closeStage();
        }
    }

    @FXML
    void textFieldAnswerAisLetter(KeyEvent event) {
        checkEditButton();
    }

    @FXML
    void textFieldAnswerBAction(ActionEvent event) {
        if (checkEditButton()) {
            editQuestion();
            closeStage();
        }
    }

    @FXML
    void textFieldAnswerBisLetter(KeyEvent event) {
        checkEditButton();
    }

    @FXML
    void textFieldAnswerCAction(ActionEvent event) {
        if (checkEditButton()) {
            editQuestion();
            closeStage();
        }
    }

    @FXML
    void textFieldAnswerCisLetter(KeyEvent event) {
        checkEditButton();
    }

    @FXML
    void textFieldAnswerDAction(ActionEvent event) {
        if (checkEditButton()) {
            editQuestion();
            closeStage();
        }
    }

    @FXML
    void textFieldAnswerDisLetter(KeyEvent event) {
        checkEditButton();
    }

    @FXML
    void textFieldQuestionIsLetter(KeyEvent event) {
        checkEditButton();
    }

    @FXML
    void textFieldQuestionNameAction(ActionEvent event) {
        if (checkEditButton()) {
            editQuestion();
            closeStage();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (image != null) {
            imgCheckMark.setVisible(true);
            buttonAddImg.setText(image.getImageName());

        } else {
            imgCheckMark.setVisible(false);
            buttonAddImg.setText(TitlePattern.NO_IMG);
        }
        comboBoxSubject.getItems().setAll(repositoryListViewer.getSubjectList());
        comboBoxSubject.getSelectionModel().select(question.getSubject());
        comboBoxTestRefresh();
        comboBoxTest.getSelectionModel().select(question.getTest());
        comboBoxSubjectChoosed.getItems().setAll(repositoryListViewer.getSubjectList());
        comboBoxSubjectChoosed.getSelectionModel().select(question.getSubject());
        comboBoxTestChoosed.getItems().setAll(question.getSubject().getTests());
        comboBoxTestChoosed.getSelectionModel().select(question.getTest());
        fillTextFields();
        markCorrectAnswer();
        checkRadioABCD();
    }

    public void setItems(Question myQuestion, TableViewQuestionThread tableViewQuestionThread) {
        question = myQuestion;
        thread = tableViewQuestionThread;
        if (myQuestion.getImage() != null) {
            image = myQuestion.getImage();
        }
    }

    public static void clean() {
        thread.notifyGuard();
        question = null;
        image = null;
        thread = null;
    }

    private void closeStage() {
        clean();
        Stage stage = (Stage) buttonClose.getScene().getWindow();
        stage.close();
    }

    private void fillTextFields() {
        textFieldQuestionName.setPromptText(String.format(LabelPattern.QUESTION_OLD_NAME, question.getQuestionName()));
        textFieldQuestionName.setText(question.getQuestionName());
        textFieldAnswerA.setPromptText(String.format(LabelPattern.QUESTION_OLD_A, question.getAnswersContent().getAnswerA()));
        textFieldAnswerA.setText(question.getAnswersContent().getAnswerA());
        textFieldAnswerB.setPromptText(String.format(LabelPattern.QUESTION_OLD_B, question.getAnswersContent().getAnswerB()));
        textFieldAnswerB.setText(question.getAnswersContent().getAnswerB());
        if (question.getQuestionType() == QuestionType.AB) {
            radioAB.setSelected(true);
        } else if (question.getQuestionType() == QuestionType.ABC) {
            radioABC.setSelected(true);
            textFieldAnswerC.setPromptText(String.format(LabelPattern.QUESTION_OLD_C, question.getAnswersContent().getAnswerC()));
            textFieldAnswerC.setText(question.getAnswersContent().getAnswerC());
        } else if (question.getQuestionType() == QuestionType.ABCD) {
            radioABCD.setSelected(true);
            textFieldAnswerC.setPromptText(String.format(LabelPattern.QUESTION_OLD_C, question.getAnswersContent().getAnswerC()));
            textFieldAnswerC.setText(question.getAnswersContent().getAnswerC());
            textFieldAnswerD.setPromptText(String.format(LabelPattern.QUESTION_OLD_D, question.getAnswersContent().getAnswerD()));
            textFieldAnswerD.setText(question.getAnswersContent().getAnswerD());
        }
    }

    private void markCorrectAnswer() {
        if (question.getAnswerCorrect() == AnswerCorrect.A) {
            radioCorrectA.setSelected(true);
        } else if (question.getAnswerCorrect() == AnswerCorrect.B) {
            radioCorrectB.setSelected(true);
        } else if (question.getAnswerCorrect() == AnswerCorrect.C) {
            radioCorrectC.setSelected(true);
        } else if (question.getAnswerCorrect() == AnswerCorrect.D) {
            radioCorrectD.setSelected(true);
        }
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

    private boolean checkEditButton() {
        if (comboBoxSubject.getSelectionModel().getSelectedItem() != null
                && comboBoxTest.getSelectionModel().getSelectedItem() != null
                && !textFieldQuestionName.getText().isBlank()
                && !textFieldAnswerA.getText().isBlank()
                && !textFieldAnswerB.getText().isBlank()) {
            if (radioAB.isSelected()) {
                buttonEditQuestion.setDisable(false);
                return true;
            }
            else if (radioABC.isSelected()
                    && !textFieldAnswerC.getText().isBlank()) {
                buttonEditQuestion.setDisable(false);
                return true;
            }
            else if (radioABCD.isSelected()
                    && !textFieldAnswerC.getText().isBlank()
                    && !textFieldAnswerD.getText().isBlank()) {
                buttonEditQuestion.setDisable(false);
                return true;
            } else {
                buttonEditQuestion.setDisable(true);
                return false;
            }
        } else {
            buttonEditQuestion.setDisable(true);
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

    private void editQuestion() {
        Subject selectedSubject = comboBoxSubject.getSelectionModel().getSelectedItem();
        Test selectedTest = comboBoxTest.getSelectionModel().getSelectedItem();
        question.setSubject(selectedSubject);
        question.setTest(selectedTest);
        if (image == null && question.getImage() != null) {
            repositoryService.removeImage(question.getImage().getImageID());
            question.setImage(null);
        } else {
            question.setImage(image);
            question.getImage().setSubject(selectedSubject);
            question.getImage().setTest(selectedTest);
            question.getImage().setQuestion(question);
        }
        question.getAnswersContent().setSubject(selectedSubject);
        question.getAnswersContent().setTest(selectedTest);
        question.getAnswersContent().setAnswerA(textFieldAnswerA.getText().trim());
        question.getAnswersContent().setAnswerB(textFieldAnswerB.getText().trim());
        if (radioAB.isSelected()) {
            question.getAnswersContent().setAnswerC(null);
            question.getAnswersContent().setAnswerD(null);
        }
        else if (radioABC.isSelected()) {
            question.getAnswersContent().setAnswerC(textFieldAnswerC.getText().trim());
            question.getAnswersContent().setAnswerD(null);
        }
        else if (radioABCD.isSelected()) {
            question.getAnswersContent().setAnswerC(textFieldAnswerC.getText().trim());
            question.getAnswersContent().setAnswerD(textFieldAnswerD.getText().trim());
        }
        question.setQuestionName(textFieldQuestionName.getText().trim());
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
        checkEditButton();
    }
}