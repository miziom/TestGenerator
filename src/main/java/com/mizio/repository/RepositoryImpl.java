package com.mizio.repository;

import com.mizio.model.Subject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RepositoryImpl implements Repository {

    SessionFactory sessionFactory;

    private Session configureAndgetSession() {
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        return sessionFactory.getCurrentSession();
    }

    private void closeSessionAndSessionFacotry(Session session) {
        session.close();
        sessionFactory.close();
    }

    @Override
    public void saveSubject(Subject subject) {
        Session session = configureAndgetSession();
        session.beginTransaction();
        session.save(subject);
        session.getTransaction().commit();
        closeSessionAndSessionFacotry(session);
    }
}
