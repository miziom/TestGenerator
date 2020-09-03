package com.mizio.main;

import com.mizio.manager.ViewManager;
import com.mizio.model.GroupDetail;
import com.mizio.repository.RepositoryListViewer;
import com.mizio.repository.RepositoryService;
import javafx.application.Application;
import javafx.stage.Stage;

import java.awt.*;

public class Program extends Application {

    @Override
    public void start(Stage stage) {
        RepositoryListViewer repositoryListViewer = new RepositoryListViewer();
        repositoryListViewer.saveOrUpdateGroupDetailList();

        if (repositoryListViewer.getGroupDetailList().size() < 3) {
            RepositoryService repositoryService = new RepositoryService();
            GroupDetail black = GroupDetail.builder()
                    .groupName("CZARNA")
                    .groupColor(new Color(0, 0, 0))
                    .build();
            GroupDetail green = GroupDetail.builder()
                    .groupName("ZIELONA")
                    .groupColor(new Color(46, 202, 103))
                    .build();
            GroupDetail blue = GroupDetail.builder()
                    .groupName("NIEBIESKA")
                    .groupColor(new Color(51, 67, 230))
                    .build();
            repositoryService.saveOrUpdateObject(black);
            repositoryService.saveOrUpdateObject(green);
            repositoryService.saveOrUpdateObject(blue);
        }
        repositoryListViewer.saveOrUpdateGroupDetailList();
        ViewManager.loadMainView();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
