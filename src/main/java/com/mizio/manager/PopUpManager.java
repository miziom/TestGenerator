package com.mizio.manager;

import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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

}
