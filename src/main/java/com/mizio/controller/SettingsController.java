package com.mizio.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXTextField;
import com.mizio.manager.ViewManager;
import com.mizio.model.GroupDetail;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

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
    private TableColumn<GroupDetail, String> columnGroupColor;

    @FXML
    private ContextMenu contextMenu;

    @FXML
    private MenuItem menuItemDelete;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void buttonAddColorAction(ActionEvent event) {

    }

    @FXML
    void buttonBackAction(ActionEvent event) {
        ViewManager.loadWindow(PathPattern.MAIN_VIEW, TitlePattern.MAIN_VIEW, event);
    }

    @FXML
    void menuItemDeleteAction(ActionEvent event) {

    }

    @FXML
    void textFieldGroupNameColorAction(ActionEvent event) {

    }

    @FXML
    void textFieldGroupNameColorIsLetter(KeyEvent event) {

    }

}
