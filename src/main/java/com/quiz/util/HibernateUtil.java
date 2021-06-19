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
public final class HibernateUtil {
    /**
     * instanca Hibernate SessionFactory klase, koja je inicijalizovana samo jedno u toku rada aplikacije
     */
    private static SessionFactory sessionFactory = null;

    /**
     * Metoda zadužena za inicijalizaciju sessionFactory polja, i inicijalnu konfiguraciju hibernate ORM-a
     */
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

    /**
     * Getter metoda koja vraća Session objekat sessionFactory-a
     * @return sesija sessionFactory-a
     */
    public static Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Getter metoda za čitanje sessionFactory polja
     * @return sessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Metoda zadužena za zatvaranje konekcije ka bazi podataka
     */
    public static void closeSessionFactory() {
        sessionFactory.close();
    }
}
