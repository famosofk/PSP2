package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChampionshipTest {

    private Championship c;
    private final int winnerScore = 102;
    private final int loserScore = 62;

    @AfterEach
    void finishTest() {
        c = null;
    }

    @Test
    void testVerifyNumberOfTeamsInChampionshipAndSucceeds() {
        c = new Championship(6);
        assertThat(c.getTeamsNumber()).isEqualTo(6);
    }

    @Test
    void testCreatingChampionshipWithOddNumberOfTeamsAndFails() {
        assertThrows(IllegalArgumentException.class, () -> new Championship(1));
    }

    @Test
    void testGetTeamException() {
        c = new Championship(5);
        assertThrows(IllegalAccessException.class, () -> c.getTeam(99));
    }

    @Test
    void testProcessMatchForWinner() {
        c = new Championship(5);
        c.processMatch("1 102 2 62");
        assertDoesNotThrow(() -> {
            Team winner = c.getTeam(1);
            assertThat(winner.getScore()).isEqualTo(MatchResult.WIN.value);
            assertThat(winner.getMadePoints()).isEqualTo(winnerScore);
            assertThat(winner.getSufferedPoints()).isEqualTo(loserScore);
        });
    }

    @Test
    void testProcessMatchForLoser() {
        c = new Championship(5);
        c.processMatch("1 102 2 62");
        assertDoesNotThrow(() -> {
            Team loser = c.getTeam(2);
            assertThat(loser.getScore()).isEqualTo(MatchResult.LOSE.value);
            assertThat(loser.getMadePoints()).isEqualTo(loserScore);
            assertThat(loser.getSufferedPoints()).isEqualTo(winnerScore);
        });
    }

    @Test
    void getFinalScoreBoard() {
        c = new Championship(5);
        c.processMatch("1 102 2 62");
        c.processMatch("1 128 3 127");
        c.processMatch("1 144 4 80");
        c.processMatch("1 102 5 101");
        c.processMatch("2 62 3 61");
        c.processMatch("2 100 4 80");
        c.processMatch("2 88 5 82");
        c.processMatch("3 79 4 90");
        c.processMatch("3 87 5 100");
        c.processMatch("4 110 5 99");
        assertThat(c.getResult()).isEqualTo("1 2 4 5 3");
    }
}