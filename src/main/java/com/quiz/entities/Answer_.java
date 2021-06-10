package com.quiz.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Answer.class)
abstract class Answer__ {
	public static volatile SingularAttribute<Answer, String> answer;
	public static volatile SingularAttribute<Answer, Question> question;
	public static volatile SingularAttribute<Answer, Integer> id;

	public static final String ANSWER = "answer";
	public static final String QUESTION = "question";
	public static final String ID = "id";

}

