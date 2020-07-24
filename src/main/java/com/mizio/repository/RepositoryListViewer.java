package com.mizio.repository;

import com.mizio.model.Subject;

import java.util.List;
import java.util.Optional;

public class RepositoryListViewer {

    private static List<Subject> subjectList;
    private RepositoryService repositoryService = new RepositoryService();

    public void saveOrUpdateList() {
        new Thread(() -> {
            subjectList = repositoryService.getSubjectsList();
        }).start();
    }

    public Subject getSubject(int ID) {
        return subjectList.stream()
                .filter(s -> s.getSubjectID() == ID).findFirst().get();
    }

    public boolean subjectIsPresent(String subjectName) {
        Optional<Subject> subject = subjectList.stream()
                .filter(s -> s.getSubjectName().equals(subjectName)).findFirst();
        return subject.isPresent();
    }

}