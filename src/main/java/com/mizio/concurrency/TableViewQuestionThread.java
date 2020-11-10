package com.mizio.concurrency;

import com.mizio.model.Question;
import com.mizio.pattern.TitlePattern;
import com.mizio.service.RepositoryListViewerService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewQuestionThread implements Runnable {

    private Question question;
    private TableView<Question> tableView;
    private TableColumn<Question, String> columnQuestionName;
    private TableColumn<Question, String> columnImage;
    private TableColumn<Question, String> columnQuestionType;
    private TableColumn<Question, String> columnA;
    private TableColumn<Question, String> columnB;
    private TableColumn<Question, String> columnC;
    private TableColumn<Question, String> columnD;
    private TableColumn<Question, String> columnCorrectAnswer;
    private Label labelQuestionCounter;

    private boolean guard = true;
    private RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();

    public TableViewQuestionThread(
            Question question,
            TableView<Question> tableView,
            TableColumn<Question, String> columnQuestionName,
            TableColumn<Question, String> columnImage,
            TableColumn<Question, String> columnQuestionType,
            TableColumn<Question, String> columnA,
            TableColumn<Question, String> columnB,
            TableColumn<Question, String> columnC,
            TableColumn<Question, String> columnD,
            TableColumn<Question, String> columnCorrectAnswer,
            Label labelQuestionCounter) {
        this.question = question;
        this.tableView = tableView;
        this.columnQuestionName = columnQuestionName;
        this.columnImage = columnImage;
        this.columnQuestionType = columnQuestionType;
        this.columnA = columnA;
        this.columnB = columnB;
        this.columnC = columnC;
        this.columnD = columnD;
        this.columnCorrectAnswer = columnCorrectAnswer;
        this.labelQuestionCounter = labelQuestionCounter;
    }

    @Override
    public synchronized void run() {
        while (guard) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        tableView.getItems().setAll(repositoryListViewer.getTest(
                question.getSubject().getSubjectID(),
                question.getTest().getTestID())
                .getQuestions());
        if(tableView.getItems().isEmpty()) {
            labelQuestionCounter.setText(String.format(TitlePattern.COUNTER_QUESTION, 0));
        } else {
            labelQuestionCounter.setText(String.format(TitlePattern.COUNTER_QUESTION, tableView.getItems().size()));
        }
        guard = true;
    }

    public synchronized void notifyGuard() {
        guard = false;
        notifyAll();
    }
}