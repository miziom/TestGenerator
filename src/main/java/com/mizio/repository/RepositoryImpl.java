package com.mizio.repository;

import com.mizio.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RepositoryImpl implements Repository {

    SessionFactory sessionFactory;
    Session session;

    private Session configureAndGetSession() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    private void closeSessionAndSessionFactory() {
        session.close();
        sessionFactory.close();
    }

    @Override
    public void saveOrUpdateSubject(Subject subject) {
        session = configureAndGetSession();
        session.beginTransaction();
        session.saveOrUpdate(subject);
        session.getTransaction().commit();
        closeSessionAndSessionFactory();
    }

    @Override
    public Subject getSubject(int subjectID) {
        session = configureAndGetSession();
        session.beginTransaction();
        Subject subject = session.get(Subject.class, subjectID);
        closeSessionAndSessionFactory();
        return subject;
    }

    @Override
    public boolean deleteSubject(int subjectID) {
        session = configureAndGetSession();
        session.beginTransaction();
        return true;
    }
}
