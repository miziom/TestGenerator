package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.model.GroupDetail;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import com.mizio.service.RepositoryListViewerService;
import com.mizio.service.GroupDetailService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    private RepositoryListViewerService repositoryListViewer = new RepositoryListViewerService();
    private GroupDetailService groupDetailService = new GroupDetailService();

    @FXML
    private JFXButton buttonBack;

    @FXML
    private JFXColorPicker colorPicker;

    @FXML
    private JFXTextField textFieldGroupNameColor;

    @FXML
    private JFXButton buttonAddColor;

    @FXML
    private TableView<GroupDetail> tableView;

    @FXML
    private TableColumn<GroupDetail, String> columnGroupName;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        checkAddGroupDetailButton();
        tableViewRefresh();
    }

    @FXML
    void buttonAddColorAction(ActionEvent event) {
        groupDetailService.addGroupDetail(
                (int) (colorPicker.getValue().getRed() * 255),
                (int) (colorPicker.getValue().getGreen() * 255),
                (int) (colorPicker.getValue().getBlue() * 255),
                textFieldGroupNameColor.getText().trim()
        );
        tableViewRefresh();
    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void menuItemDeleteAction(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItem() != null) {
            groupDetailService.deleteGroupDetail(tableView.getSelectionModel().getSelectedItem().getGroupID());
            tableViewRefresh();
        }
    }

    @FXML
    void textFieldGroupNameColorAction(ActionEvent event) {
        if (checkAddGroupDetailButton()) {
            groupDetailService.addGroupDetail(
                    (int) (colorPicker.getValue().getRed() * 255),
                    (int) (colorPicker.getValue().getGreen() * 255),
                    (int) (colorPicker.getValue().getBlue() * 255),
                    textFieldGroupNameColor.getText().trim()
            );
        }
        tableViewRefresh();
    }

    @FXML
    void textFieldGroupNameColorIsLetter(KeyEvent event) {
        checkAddGroupDetailButton();
    }

    private void tableViewRefresh() {
        tableView.getItems().clear();
        tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        columnGroupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        tableView.setRowFactory(row -> new TableRow<>(){
            @Override
            protected void updateItem(GroupDetail groupDetail, boolean b) {
                super.updateItem(groupDetail, b);
                if (groupDetail == null) {
                    setStyle("");
                } else {
                    setStyle(String.format("-fx-background-color: rgb(%d,%d,%d);",
                            groupDetail.getGroupColor().getRed(),
                            groupDetail.getGroupColor().getGreen(),
                            groupDetail.getGroupColor().getBlue()));
                }
            }
        });
        tableView.getItems().setAll(repositoryListViewer.getGroupDetailList());
    }

    private boolean checkAddGroupDetailButton() {
        if (textFieldGroupNameColor.getText().trim().isBlank()) {
            buttonAddColor.setDisable(true);
            return false;
        } else {
            buttonAddColor.setDisable(false);
            return true;
        }
    }
}