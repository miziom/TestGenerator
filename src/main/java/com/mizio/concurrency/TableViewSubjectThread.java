package com.mizio.concurrency;

import com.mizio.model.Subject;
import com.mizio.repository.RepositoryListViewer;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewSubjectThread implements Runnable {

    private TableView<Subject> tableView;
    private TableColumn<Subject, String> columnSubjectName;

    private boolean guard = true;
    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();

    public TableViewSubjectThread(TableView<Subject> tableView, TableColumn<Subject, String> columnSubjectName) {
        this.tableView = tableView;
        this.columnSubjectName = columnSubjectName;
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
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subjectName"));
        tableView.getItems().setAll(repositoryListViewer.getSubjectList());
        guard = true;
    }

    public synchronized void notifyGuard() {
        guard = false;
        notifyAll();
    }
}
