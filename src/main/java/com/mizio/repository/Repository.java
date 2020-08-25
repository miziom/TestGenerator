package com.mizio.repository;

import com.mizio.model.Subject;

import java.util.List;

public interface Repository {

    <T> void saveOrUpdateObject(T object);
    <T> T getObject(Class<T> tClass, int ID);
    <T> boolean deleteObject(Class<T> tClass, int ID);
    void removeImage(int id);
    List<Subject> getSubjectsList();
}