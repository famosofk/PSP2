package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class ChampionshipTest {

    private Championship c;

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
        try {
            new Championship(1);
            wrongResult();
        } catch (Exception e) {
            assertThat(true).isTrue();
        }
    }

    @Test
    void testGetTeamException() {
        try {
            c = new Championship(5);
            c.getTeam(99);
            wrongResult();
        } catch (IllegalAccessException e) {
            assertThat(true).isTrue();
        }
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

    private void wrongResult() {
        assertThat(true).isFalse();
    }
}