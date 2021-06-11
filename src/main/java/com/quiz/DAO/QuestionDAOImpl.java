package com.quiz.DAO;

import com.quiz.entities.Question;
import com.quiz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class QuestionDAOImpl {
    public QuestionDAOImpl(){}

    public List<Question> getQuestions(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx =  session.beginTransaction();

        List<Question> questions = session.createQuery("from Question").getResultList();

        tx.commit();

        return questions;
    }
}
