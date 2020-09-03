package com.mizio.repository;

import com.mizio.model.GroupDetail;
import com.mizio.model.Subject;
import com.mizio.model.Test;

import java.util.List;
import java.util.Optional;

public class RepositoryListViewer {

    private static List<GroupDetail> groupDetailList;
    private static List<Subject> subjectList;
    private RepositoryService repositoryService = new RepositoryService();

    public void saveOrUpdateList() {
        subjectList = repositoryService.getSubjectsList();
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }
    public void saveOrUpdateGroupDetailList() {
        groupDetailList = repositoryService.getGroupDetailList();
    }

    public List<GroupDetail> getGroupDetailList() {
        return groupDetailList;
    }

    public Subject getSubject(int ID) {
        Optional<Subject> subjectOptional = subjectList.stream()
                .filter(s -> s.getSubjectID() == ID)
                .findFirst();
        return subjectOptional.orElse(null);
    }

    public boolean subjectIsPresent(String subjectName) {
        Optional<Subject> subject = subjectList.stream()
                .filter(s -> s.getSubjectName().equals(subjectName))
                .findFirst();
        return subject.isPresent();
    }

    public Test getTest(int subjectID, int testID) {
        Optional<Subject> subjectOptional = subjectList.stream()
                .filter(subject -> subject.getSubjectID() == subjectID)
                .findFirst();
        if (subjectOptional.isPresent()) {
            Optional<Test> testOptional = subjectOptional.get()
                    .getTests()
                    .stream()
                    .filter(test -> test.getTestID() == testID)
                    .findFirst();
            return testOptional.orElse(null);
        } else {
            return null;
        }
    }

    public boolean testIsPresent(int subjectID, String testName) {
        Optional<Subject> subjectOptional = subjectList.stream()
                .filter(subject -> subject.getSubjectID() == subjectID)
                .findFirst();
        if (subjectOptional.isPresent()) {
            Optional<Test> testOptional = subjectOptional.get()
                    .getTests()
                    .stream()
                    .filter(test -> test.getTestName().equals(testName))
                    .findFirst();
            return testOptional.isPresent();
        } else {
            return false;
        }
    }

}