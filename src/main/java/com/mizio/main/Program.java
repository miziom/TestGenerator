package com.mizio.main;

import com.mizio.manager.ViewManager;
import com.mizio.service.GroupDetailService;
import javafx.application.Application;
import javafx.stage.Stage;

public class Program extends Application {

    @Override
    public void start(Stage stage) {

        new GroupDetailService().loadExampleGroupDetail();

        ViewManager.loadMainView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
