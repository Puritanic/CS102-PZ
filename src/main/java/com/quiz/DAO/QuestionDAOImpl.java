package com.quiz.DAO;

import com.quiz.entities.Question;
import com.quiz.interfaces.QuestionDAO;
import com.quiz.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Data Access Object za Question klasu. Koristi se Question kontroler klasi za komunikaciju za bazom.
 */
public class QuestionDAOImpl implements QuestionDAO {
    public QuestionDAOImpl(){}

    @Override
    public List<Question> getQuestions() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx =  session.beginTransaction();

        List<Question> questions = session.createQuery("from Question").getResultList();

        tx.commit();

        return questions;
    }
}
