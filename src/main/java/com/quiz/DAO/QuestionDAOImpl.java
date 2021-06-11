package com.quiz.DAO;

import com.quiz.entities.Question;
import com.quiz.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class QuestionDAOImpl {
    public QuestionDAOImpl(){}

    public List<Question> getQuestions(){
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();

        List<Question> questions = session.createQuery("from Question").getResultList();

        session.getTransaction().commit();

        return questions;
    }
}
