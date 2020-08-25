package com.mizio.repository;

import com.mizio.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class RepositoryImpl implements Repository {

    private SessionFactory sessionFactory;
    private Session session;

    private void configureSessionAndBeginTransaction() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
    }

    private void closeSessionAndSessionFactory() {
        session.close();
        sessionFactory.close();
    }

    @Override
    public <T> void saveOrUpdateObject(T object) {
        configureSessionAndBeginTransaction();
        session.saveOrUpdate(object);
        session.getTransaction().commit();
        closeSessionAndSessionFactory();
    }

    @Override
    public <T> T getObject(Class<T> tClass, int ID) {
        configureSessionAndBeginTransaction();
        Object object = session.get(tClass, ID);
        closeSessionAndSessionFactory();
        return tClass.cast(object);
    }

    @Override
    public <T> boolean deleteObject(Class<T> tClass, int ID) {
        configureSessionAndBeginTransaction();
        Object object = session.load(tClass, ID);
        if(object != null) {
            session.delete(tClass.cast(object));
            session.getTransaction().commit();
            closeSessionAndSessionFactory();
            return true;
        }
        closeSessionAndSessionFactory();
        return false;
    }

    @Override
    public void removeImage(int id) {
        configureSessionAndBeginTransaction();
        session.createQuery("delete from Image where imageID = :ID")
                .setParameter("ID", id)
                .executeUpdate();
        closeSessionAndSessionFactory();
    }

    @Override
    public List<Subject> getSubjectsList() {
        configureSessionAndBeginTransaction();
        List<Subject> subjects = session.createQuery("from Subject", Subject.class).list();
        closeSessionAndSessionFactory();
        return subjects;
    }
}