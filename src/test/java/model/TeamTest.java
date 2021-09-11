package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class TeamTest {

    private Championship c;
    private int winningTeam = 1;
    private int winnerScore = 102;
    private int losingTeam = 2;
    private int loserScore = 62;
    private String match = String.format("%d %d %d %d", winningTeam, winnerScore, losingTeam, loserScore);


    @BeforeEach
    void setup() {
        c = new Championship(6);
    }

    @Test
    void testProcessWinMatch() {
        c.processMatch(match);
        try {
            Team winner = c.getTeam(winningTeam);

            assertThat(winner.getScore()).isEqualTo(MatchResult.WIN.value);
            assertThat(winner.getMadePoints()).isEqualTo(winnerScore);
            assertThat(winner.getSufferedPoints()).isEqualTo(loserScore);
        } catch (Exception e) {
            wrongResult();
        }
    }

    @Test
    void testProcessLoseMatch() {
        c.processMatch(match);
        try {
            Team loser = c.getTeam(losingTeam);
            assertThat(loser.getScore()).isEqualTo(MatchResult.LOSE.value);
            assertThat(loser.getMadePoints()).isEqualTo(loserScore);
            assertThat(loser.getSufferedPoints()).isEqualTo(winnerScore);
        } catch (Exception e) {
            wrongResult();
        }
    }

    @Test
    void testAddScoredPointsToTeam() {
        c.processMatch(match);
        try {
            Team loser = c.getTeam(losingTeam);
            assertThat(loser.getMadePoints()).isEqualTo(loserScore);
        } catch (Exception e) {
            wrongResult();
        }

    }

    @Test
    void testAddSufferedPointsToTeam() {
        c.processMatch(match);
        try {
            Team loser = c.getTeam(losingTeam);
            assertThat(loser.getSufferedPoints()).isEqualTo(winnerScore);
        } catch (Exception e) {
            wrongResult();
        }
    }

    private void wrongResult() {
        assertThat(true).isFalse();
    }

}