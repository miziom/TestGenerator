package com.mizio.repository;

import com.mizio.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RepositoryImpl implements Repository {

    SessionFactory sessionFactory;
    Session session;

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
    public void saveOrUpdateSubject(Subject subject) {
        configureSessionAndBeginTransaction();
        session.saveOrUpdate(subject);
        session.getTransaction().commit();
        closeSessionAndSessionFactory();
    }

    @Override
    public Subject getSubject(int subjectID) {
        configureSessionAndBeginTransaction();
        Subject subject = session.get(Subject.class, subjectID);
        closeSessionAndSessionFactory();
        return subject;
    }

    @Override
    public boolean deleteSubject(int subjectID) {
        configureSessionAndBeginTransaction();
        Subject subject = session.load(Subject.class, subjectID);
        if(subject != null) {
            session.delete(subject);
            session.getTransaction().commit();
            return true;
        }
        return false;
    }
}
