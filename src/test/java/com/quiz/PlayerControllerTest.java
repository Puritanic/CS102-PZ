package com.quiz;

import com.quiz.DAO.PlayerDAOImpl;
import com.quiz.app.controllers.AuthController;
import com.quiz.app.controllers.ScreenController;
import com.quiz.entities.Player;
import com.quiz.exceptions.AuthException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.*;


public class PlayerControllerTest {
    @Mock
    private final PlayerDAOImpl playerServiceTestMock = new PlayerDAOImpl();

    @InjectMocks
    private AuthController authControllerMock;

    @InjectMocks
    private ScreenController screenControllerMock;

    @Captor
    private ArgumentCaptor<Player> playerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginPlayerTest() throws AuthException {
        Player player = new Player("test_username", "test@test.com", "passss", false);
        playerServiceTestMock.login("test@test.com", "passss");

        when(playerServiceTestMock.login("test@test.com", "passss")).thenReturn(player);

        org.hamcrest.MatcherAssert.assertThat(playerServiceTestMock.login("test@test.com", "passss"), is(notNullValue()));
    }

    @Test
    public void registerPlayerTest() {
        Player player = new Player("test_username", "test@test.com", "passss", false);

        playerServiceTestMock.register(player);

        // proveri da li je metod izvršen
        verify(playerServiceTestMock).register(playerArgumentCaptor.capture());

        // proveri da li je metod izvršen samo jednom
        verify(playerServiceTestMock, times(1)).register(player);

        // proveri da li je sacuvani Player imao email adresu pri registraciji
        org.hamcrest.MatcherAssert.assertThat(playerArgumentCaptor.getValue().getEmail(), is(notNullValue()));
    }

    @Test
    public void updatePlayerTest() {
        Player player = new Player("test_username", "test@test.com", "passss", false);

        playerServiceTestMock.updatePlayer(player);

        // proveri da li je metod izvršen
        verify(playerServiceTestMock).updatePlayer(playerArgumentCaptor.capture());

        // proveri da li je metod izvršen samo jednom
        verify(playerServiceTestMock, times(1)).updatePlayer(player);

        // proveri da li je sačuvani Player imao email adresu pri update-u
        org.hamcrest.MatcherAssert.assertThat(playerArgumentCaptor.getValue().getEmail(), is(notNullValue()));
    }

    @Test
    public void getPlayerDataTest() {
        List<Player> players = new ArrayList<>();
        when(playerServiceTestMock.getPlayerData()).thenReturn(players);
        org.hamcrest.MatcherAssert.assertThat(playerServiceTestMock.getPlayerData(), is(notNullValue()));
    }
}
