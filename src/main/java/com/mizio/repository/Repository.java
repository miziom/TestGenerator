package com.mizio.repository;

import com.mizio.model.Subject;

public interface Repository {

    void saveOrUpdateSubject(Subject subject);
    Subject getSubject(int subjectID);
    boolean deleteSubject(int subjectID);

}
