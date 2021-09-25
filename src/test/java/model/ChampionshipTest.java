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
        c.processMatch(102, 62, 1, 2);
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
        c.processMatch(102, 62, 1, 2);
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
        c.processMatch(102, 62, 1, 2);
        c.processMatch(128, 127, 1, 3);
        c.processMatch(144, 80, 1, 4);
        c.processMatch(102, 101, 1, 5);
        c.processMatch(62, 61, 2, 3);
        c.processMatch(100, 80, 2, 4);
        c.processMatch(88, 82, 2, 5);
        c.processMatch(79, 90, 3, 4);
        c.processMatch(87, 100, 3, 5);
        c.processMatch(110, 99, 4, 5);
        assertThat(c.getResult()).isEqualTo("1 2 4 5 3");
    }
}