package com.quiz;

import com.quiz.DAO.QuestionDAOImpl;
import com.quiz.entities.Question;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;

public class QuestionControllerTest {
    @Mock
    private final QuestionDAOImpl questionServiceMock = new QuestionDAOImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Provera rada metode getQuestions, da li se poziva i da li je return vrednost odgovarajuća (not null)
     */
    @Test
    public void getQuestionsTest() {
        List<Question> questions = new ArrayList<>();
        when(questionServiceMock.getQuestions()).thenReturn(questions);
        org.hamcrest.MatcherAssert.assertThat(questionServiceMock.getQuestions(), is(notNullValue()));
    }
}
