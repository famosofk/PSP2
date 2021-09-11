package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class TeamTest {

    private Championship c;
    private final int winningTeam = 1;
    private final int winnerScore = 102;
    private final int losingTeam = 2;
    private final int loserScore = 62;
    private final String match = String.format("%d %d %d %d", winningTeam, winnerScore, losingTeam, loserScore);


    @BeforeEach
    void setup() {
        c = new Championship(6);
    }

    @Test
    void testAddScoredPointsToTeam() {
        c.processMatch(match);
        assertDoesNotThrow(() -> {
            Team loser = c.getTeam(losingTeam);
            assertThat(loser.getMadePoints()).isEqualTo(loserScore);
        });
    }

    @Test
    void testAddSufferedPointsToTeam() {
        c.processMatch(match);
        assertDoesNotThrow(() -> {
            Team loser = c.getTeam(losingTeam);
            assertThat(loser.getSufferedPoints()).isEqualTo(winnerScore);
        });
    }
}