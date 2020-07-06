package com.mizio.repository;

import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;

public interface Repository {

    <T> void saveOrUpdateObject(T object);
    <T> T getObject(Class<T> tClass, int ID);
    <T> boolean deleteObject(Class<T> tClass, int ID);

    void saveOrUpdateSubject(Subject subject);
    Subject getSubject(int subjectID);
    boolean deleteSubject(int subjectID);

    void saveOrUpdateTest(Test test);
    Test getTest(int testID);
    boolean deleteTest(int testID);

    void saveOrUpdateQuestion(Question question);
    Question getQuestion(int questionID);
    boolean deleteQuestion(int questionID);
}
