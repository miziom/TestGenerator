package com.mizio.concurrency;

import com.mizio.model.Test;
import com.mizio.repository.RepositoryListViewer;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewTestThread implements Runnable {

    private TableView<Test> tableView;
    private TableColumn<Test, String> columnTestName;
    private int subjectID;

    private boolean guard = true;
    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();

    public TableViewTestThread(TableView<Test> tableView, TableColumn<Test, String> columnTestName, int subjectID) {
        this.tableView = tableView;
        this.columnTestName = columnTestName;
        this.subjectID = subjectID;
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
        columnTestName.setCellValueFactory(new PropertyValueFactory<>("testName"));
        tableView.getItems().setAll(repositoryListViewer.getSubject(subjectID).getTests());
        guard = true;
    }

    public synchronized void notifyGuard() {
        guard = false;
        notifyAll();
    }
}
