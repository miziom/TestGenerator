package com.mizio.repository;

import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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

    @Override
    public void saveOrUpdateTest(Test test) {
        configureSessionAndBeginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        closeSessionAndSessionFactory();
    }

    @Override
    public Test getTest(int testID) {
        configureSessionAndBeginTransaction();
        Test test = session.get(Test.class, testID);
        closeSessionAndSessionFactory();
        return test;
    }

    @Override
    public boolean deleteTest(int testID) {
        configureSessionAndBeginTransaction();
        Test test = session.find(Test.class, testID);
        if(test != null) {
            session.delete(test);
            session.getTransaction().commit();
            closeSessionAndSessionFactory();
            return true;
        }
        return false;
    }

    @Override
    public void saveOrUpdateQuestion(Question question) {
        configureSessionAndBeginTransaction();
        session.saveOrUpdate(question);
        session.getTransaction().commit();
        closeSessionAndSessionFactory();
    }

    @Override
    public Question getQuestion(int questionID) {
        configureSessionAndBeginTransaction();
        Question question = session.get(Question.class, questionID);
        closeSessionAndSessionFactory();
        return question;
    }

    @Override
    public boolean deleteQuestion(int questionID) {
        configureSessionAndBeginTransaction();
        Question question = session.find(Question.class, questionID);
        if(question != null) {
            session.delete(question);
            session.getTransaction().commit();
            closeSessionAndSessionFactory();
            return true;
        }
        return false;
    }
}
