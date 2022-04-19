import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

// mock hour
// spy prison
@ExtendWith(MockitoExtension.class)
class StrangeGameTest {

    @Mock
    private Hour hourStub = Mockito.mock(Hour.class);
    @Mock
    private GAMEDb GAMEDbStub = Mockito.mock(GAMEDb.class);


    @Spy
    private Prison prisonSpy;


    @InjectMocks
    private StrangeGame strangeGame = new StrangeGame();


    @Test
    void test_a() throws InterruptedException {
        Player player = new Player();

        Mockito.when(hourStub.getHour()).thenReturn(0);
        assertEquals("The game is not yet open!", strangeGame.enterGame(player));

        Mockito.when(hourStub.getHour()).thenReturn(11);
        assertEquals("The game is not yet open!", strangeGame.enterGame(player));

        assertEquals(0, prisonSpy.getLog().size());
        Mockito.verify(prisonSpy,Mockito.never()).crime(Mockito.any());

    }

    @Test
    void test_b() throws InterruptedException {
        Player player = new Player("9527", 0);
        Mockito.doNothing().when(prisonSpy).imprisonment(Mockito.any(Player.class));

        Mockito.when(hourStub.getHour()).thenReturn(12);
        assertEquals("Have a nice day!", strangeGame.enterGame(player));

        Mockito.when(hourStub.getHour()).thenReturn(23);
        assertEquals("Have a nice day!", strangeGame.enterGame(player));

        assertEquals(0, prisonSpy.getLog().size());
        Mockito.verify(prisonSpy,Mockito.never()).crime(Mockito.any());

        Player player2 = new Player();

        Mockito.when(hourStub.getHour()).thenReturn(12);
        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame.enterGame(player2));

        Mockito.when(hourStub.getHour()).thenReturn(23);
        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame.enterGame(player2));

        assertEquals(2, prisonSpy.getLog().size());
        Mockito.verify(prisonSpy,Mockito.times(2)).crime(Mockito.any());


    }

    @Test
    void test_c() throws InterruptedException {
        Mockito.doNothing().when(prisonSpy).imprisonment(Mockito.any());
        Mockito.when(hourStub.getHour()).thenReturn(12);

        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame.enterGame(new Player("9527", -1)));
        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame.enterGame(new Player("9528", -1)));
        assertEquals("After a long period of punishment, the player can leave! :)", strangeGame.enterGame(new Player("9529", -1)));

        ArrayList prisonerLog = prisonSpy.getLog();

        assertEquals(3, prisonerLog.size());
        Mockito.verify(prisonSpy,Mockito.times(3)).crime(Mockito.any());
        assertEquals("9527", prisonerLog.get(0));
        assertEquals("9528", prisonerLog.get(1));
        assertEquals("9529", prisonerLog.get(2));

    }

    @Test
    void test_d() {
        Mockito.when(GAMEDbStub.getScore(Mockito.anyString())).thenReturn(1);

        assertEquals(1, strangeGame.getScore("310552020"));

    }

    @Test
    void test_e() {
        assertEquals("Thank you", strangeGame.donate(new paypalService() {
            @Override
            public String doDonate() {
                return "Success";
            }
        }));

        assertEquals("Some errors occurred", strangeGame.donate(new paypalService() {
            @Override
            public String doDonate() {
                return "Error";
            }
        }));

    }

}