package com.mizio.service;
import com.mizio.model.GroupDetail;
import com.mizio.model.Subject;
import com.mizio.repository.Repository;
import com.mizio.repository.RepositoryImpl;

import java.util.List;

public class RepositoryService {

    private Repository repository = new RepositoryImpl();

    public <T> void saveOrUpdateObject(T object) {
        repository.saveOrUpdateObject(object);
    }

    public <T> T getObject(Class<T> tClass, int ID) {
        return repository.getObject(tClass, ID);
    }

    public <T> boolean deleteObject(Class<T> tClass, int ID) {
        return repository.deleteObject(tClass, ID);
    }

    public void removeImage(int id) {
        repository.removeImage(id);
    }

    public List<Subject> getSubjectsList() {
        return repository.getSubjectsList();
    }
    public List<GroupDetail> getGroupDetailList() {
        return repository.getGroupDetailList();
    }
}