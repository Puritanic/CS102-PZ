package com.quiz.DAO;

import com.quiz.entities.Player;
import com.quiz.exceptions.AuthException;
import com.quiz.util.HibernateUtil;
import com.quiz.util.PasswordUtils;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class PlayerDAOImpl {
    public PlayerDAOImpl(){}

    public void login(String email, String password) throws AuthException {
        Session session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Player> query = builder.createQuery(Player.class);
        Root<Player> root = query.from(Player.class);
        query.where(builder.equal(root.get("email"), email));
        TypedQuery<Player> q = session.createQuery(query);
        Player result =  q.getSingleResult();
        session.getTransaction().commit();

        boolean correctPass = PasswordUtils.checkPassword(password, result.getPassword());

        if(!correctPass){
            throw new AuthException("Login Failed");
        }

        System.out.println(result);
        System.out.println("LOGIN?");
    }

    public void register(Player newPlayer) {
        Session s = HibernateUtil.getCurrentSession();
        s.beginTransaction();
        s.save(newPlayer);
        s.getTransaction().commit();
    }
}
