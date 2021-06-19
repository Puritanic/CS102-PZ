package com.quiz.DAO;

import com.quiz.entities.Answer;
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
    /**
     * Podrazumevani konstruktor
     */
    public QuestionDAOImpl(){}

    /**
     * @return lista pitanja iz baze podataka
     */
    @Override
    public List<Question> getQuestions() {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx =  session.beginTransaction();

        List<Question> questions = session.createQuery("from Question").getResultList();

        tx.commit();

        return questions;
    }

    /**
     * @param q         Pitanje koje želimo da sačuvamo
     * @param answers   Lista odgovora za dato pitanje
     * @param answerIdx indeks tačnog odgovora u listi pitanja
     */
    @Override
    public void saveQuestion(Question q, List<Answer> answers, int answerIdx) {
        Session session = HibernateUtil.getCurrentSession();
        Transaction tx =  session.beginTransaction();

        try {
            session.save(q);

            for (Answer a : answers) {
                a.setQuestion(q);
                session.save(a);
            }

            Answer cA = answers.get(answerIdx);
            q.setCorrectAnswerId(cA.getId());
            session.update(q);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            tx.commit();
        }
    }
}
