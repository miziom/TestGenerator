package com.mizio.manager;

import com.mizio.controller.EditSubjectController;
import com.mizio.controller.EditTestController;
import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import com.mizio.pattern.PathPattern;
import com.mizio.pattern.TitlePattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.SneakyThrows;

public class ViewManager {

    @SneakyThrows
    public static void loadMainView() {
        Parent parent = FXMLLoader.load(ViewManager.class.getResource(PathPattern.MAIN_VIEW));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(parent));
        stage.setTitle(TitlePattern.MAIN_VIEW);
        stage.getIcons().add(new Image(ViewManager.class.getResourceAsStream(PathPattern.ICON_APP)));
        stage.show();
    }

    @SneakyThrows
    public static void loadWindow(String path, String title, ActionEvent event) {
        Parent parent = FXMLLoader.load(ViewManager.class.getResource(path));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        Scene scene = new Scene(parent, stage.getScene().getWidth(), stage.getScene().getHeight());
        stage.setScene(scene);
        stage.show();
    }

    @SneakyThrows
    public static <T> void loadNewWindow(String path, String title, Class<T> tClass) {
        Parent parent = FXMLLoader.load(ViewManager.class.getResource(path));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setScene(new Scene(parent));
        stage.setTitle(title);
        stage.getIcons().add(new Image(ViewManager.class.getResourceAsStream(PathPattern.ICON_APP)));
        stage.setOnCloseRequest(windowEvent -> {
            if (tClass == Subject.class) {
                EditSubjectController.clean();
            }
            else if (tClass == Test.class) {
                EditTestController.clean();
            }
            else if (tClass == Question.class) {

            }
        });
        stage.show();
    }
}
