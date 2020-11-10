package com.mizio.service;

import com.mizio.model.GroupDetail;
import com.mizio.repository.RepositoryListViewer;

import java.awt.*;

public class GroupDetailService {

    private RepositoryListViewer repositoryListViewer = new RepositoryListViewer();
    private RepositoryService repositoryService = new RepositoryService();

    public void loadExampleGroupDetail() {
        repositoryListViewer.saveOrUpdateGroupDetailList();
        if (repositoryListViewer.getGroupDetailList().size() < 3) {
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
    }

    public void addGroupDetail(int redValue, int greenValue, int blueValue, String groupName) {
        GroupDetail groupDetail = GroupDetail.builder()
                .groupName(groupName.toUpperCase())
                .groupColor(new Color(redValue, greenValue, blueValue))
                .build();
        repositoryService.saveOrUpdateObject(groupDetail);
        repositoryListViewer.saveOrUpdateGroupDetailList();
    }

    public boolean deleteGroupDetail(int groupDetailID) {
        if (repositoryListViewer.getGroupDetailList().size() <= 3) {
            return false;
        } else {
            repositoryService.deleteObject(GroupDetail.class, groupDetailID);
            repositoryListViewer.saveOrUpdateGroupDetailList();
            return true;
        }
    }
}
