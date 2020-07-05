package com.mizio.repository;

import com.mizio.model.Question;
import com.mizio.model.Subject;
import com.mizio.model.Test;
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

    @Override
    public void saveOrUpdateTest(int subjectID, Test test) {
        configureSessionAndBeginTransaction();
        Subject subject = session.load(Subject.class, subjectID);
        test.setSubject(subject);
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
