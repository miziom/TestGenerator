package com.mizio.repository;
import com.mizio.model.Subject;
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

    public List<Subject> getSubjectsList() {
        return repository.getSubjectsList();
    }
}