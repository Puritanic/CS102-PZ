package com.quiz.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.quiz.entities.Answer;
import com.quiz.entities.Player;
import com.quiz.entities.Question;

/**
 * Util klasa za Hibernate, služi za inicijalno povezivanje sa bazom padataka
 * i za izvršavanje svih transakcija u toku rada aplikacije.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    public static void createSessionFactory() {
        try {
            Configuration config = new Configuration().configure("hibernate.cfg.xml");
            config.addAnnotatedClass(Player.class);
            config.addAnnotatedClass(Question.class);
            config.addAnnotatedClass(Answer.class);
            sessionFactory = config.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
