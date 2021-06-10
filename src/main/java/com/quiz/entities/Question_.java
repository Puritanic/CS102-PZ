package com.quiz.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Question.class)
public abstract class Question_ {

	public static volatile SingularAttribute<Question, String> question;
	public static volatile ListAttribute<Question, Answer> answers;
	public static volatile SingularAttribute<Question, Integer> id;
	public static volatile SingularAttribute<Question, Answer> correctAnswer;

	public static final String QUESTION = "question";
	public static final String ANSWERS = "answers";
	public static final String ID = "id";
	public static final String CORRECT_ANSWER = "correctAnswer";

}

