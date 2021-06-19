package com.quiz;

import com.quiz.app.controllers.GameController;
import com.quiz.entities.Answer;
import com.quiz.entities.Player;
import com.quiz.entities.Question;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameControllerTest {
    @Test
    public void testQuickGameInit() {
        List<Answer> aList = new ArrayList<>();
        List<Question> qList = new ArrayList<>();
        Question q = new Question("Pitanje?");
        Answer a = new Answer("Odgovor");
        aList.add(a);
        q.setAnswers(aList);
        qList.add(q);
        qList.add(q);
        qList.add(q);
        qList.add(q);
        qList.add(q);

        GameController gc = new GameController(qList);

        Assert.assertNotNull(gc.getNextQuestion());

        Assert.assertNotNull(gc.getCurrentQuestion());

        Assert.assertSame(gc.getCurrentQuestion(), qList.get(0));
    }

    @Test
    public void testGameInitWithPlayer() {
        List<Answer> aList = new ArrayList<>();
        List<Question> qList = new ArrayList<>();
        Player p = new Player("test_player", "test@test.com", "pass", false);
        Question q = new Question("Pitanje?");
        Answer a = new Answer("Odgovor");
        aList.add(a);
        q.setAnswers(aList);
        qList.add(q);
        qList.add(q);
        qList.add(q);
        qList.add(q);
        qList.add(q);

        GameController gc = new GameController(qList, p);

        Assert.assertNotNull(gc.getNextQuestion());

        Assert.assertNotNull(gc.getCurrentQuestion());

        Assert.assertSame(gc.getCurrentQuestion(), qList.get(0));

        Assert.assertSame(gc.getPlayer(), p);

        gc.getNextQuestion();
        gc.getNextQuestion();
        gc.getNextQuestion();
        gc.getNextQuestion();
        gc.getNextQuestion();

        Assert.assertNull(gc.getNextQuestion());
    }
}
