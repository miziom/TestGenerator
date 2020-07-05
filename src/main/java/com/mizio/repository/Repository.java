package com.mizio.repository;

import com.mizio.model.Subject;
import com.mizio.model.Test;

public interface Repository {

    void saveOrUpdateSubject(Subject subject);
    Subject getSubject(int subjectID);
    boolean deleteSubject(int subjectID);

    void saveOrUpdateTest(int subjectID, Test test);
    Test getTest(int testID);
    boolean deleteTest(int testID);

}
