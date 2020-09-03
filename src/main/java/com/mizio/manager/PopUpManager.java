package com.mizio.manager;

import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Optional;

public class PopUpManager {

    public static <T> boolean deleteItems(ObservableList<T> objectsList) {
        if (objectsList.isEmpty()) {
            return false;
        } else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(PopUpManager.class.getResourceAsStream(PathPattern.ICON_APP)));
            alert.setTitle(TitlePattern.DELETE_PUP_UP);
            alert.setContentText(String.format(TitlePattern.DELETE_AMOUNT_PUP_UP, objectsList.size()));
            ButtonType buttonYes = new ButtonType(TitlePattern.YES, ButtonBar.ButtonData.YES);
            ButtonType buttonNo = new ButtonType(TitlePattern.NO, ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(buttonYes, buttonNo);
            Optional<ButtonType> result = alert.showAndWait();
            return result.get() == buttonYes;
        }
    }

    public static File directoryChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "Images", "*.jpg", "*.jpeg", "*.png", "*.bmp", "*.tiff"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    }

    public static File saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter(
                "All Files", "*.*"
        );
        fileChooser.getExtensionFilters().add(extensionFilter);
        return fileChooser.showSaveDialog(((Node) event.getSource()).getScene().getWindow());
    }

    public static void showInformation(String infoText) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(PopUpManager.class.getResourceAsStream(PathPattern.ICON_APP)));
        alert.setTitle(TitlePattern.INFO_POP_UP);
        alert.setContentText(infoText);
        ButtonType buttonOk = new ButtonType(TitlePattern.OK, ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonOk);
        alert.showAndWait();
    }
}
