package com.quiz.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Player.class)
abstract class Player__ {
	public static volatile SingularAttribute<Player, String> password;
	public static volatile SingularAttribute<Player, Integer> finishedGames;
	public static volatile SingularAttribute<Player, Integer> totalPoints;
	public static volatile SingularAttribute<Player, Integer> id;
	public static volatile SingularAttribute<Player, Boolean> isAdmin;
	public static volatile SingularAttribute<Player, String> email;
	public static volatile SingularAttribute<Player, String> username;

	public static final String PASSWORD = "password";
	public static final String FINISHED_GAMES = "finishedGames";
	public static final String TOTAL_POINTS = "totalPoints";
	public static final String ID = "id";
	public static final String IS_ADMIN = "isAdmin";
	public static final String EMAIL = "email";
	public static final String USERNAME = "username";

}

