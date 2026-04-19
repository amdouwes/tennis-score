package org.smail.tennis.score;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TennisScoringTest {

    private TennisScoring scoring;

    @BeforeEach
    void setUp() {
        scoring = new TennisScoring();
    }

    @Test
    void shouldReturnGameNotFinishedWhenSequenceIncomplete() {
        GameResult result = scoring.computeScore("AAB");
        assertEquals('X', result.getWinner());
        assertTrue(result.getSequence().contains("Game not finished"));
    }

    @Test
    void shouldReturnGameNotFinishedForInProgressGame() {
        GameResult result = scoring.computeScore("ABBA");
        assertEquals('X', result.getWinner());
        assertTrue(result.getSequence().contains("Game not finished"));
    }

    @Test
    void shouldPrintScoreForSpecificationExample() {
        // exemple de l'énoncé : ABABAA
        GameResult result = scoring.computeScore("ABABAA");
        assertEquals('A', result.getWinner());
        assertEquals("Player A : 15 / Player B : 0", result.getSequence().get(0));
        assertEquals("Player A : 15 / Player B : 15", result.getSequence().get(1));
        assertEquals("Player A : 30 / Player B : 15", result.getSequence().get(2));
        assertEquals("Player A : 30 / Player B : 30", result.getSequence().get(3));
        assertEquals("Player A : 40 / Player B : 30", result.getSequence().get(4));
        assertEquals("Player A wins the game", result.getSequence().get(6));
    }

    @Test
    void shouldDeclareAWinnerWhenAWinsWithoutBScoring() {
        GameResult result = scoring.computeScore("AAAA");
        assertEquals('A', result.getWinner());
        assertEquals("Player A wins the game", result.getSequence().get(4));
    }

    @Test
    void shouldDeclareAWinnerWhenBWinsWithoutAScoring() {
        GameResult result = scoring.computeScore("BBBB");
        assertEquals('B', result.getWinner());
        assertEquals("Player B wins the game", result.getSequence().get(4));
    }

    @Test
    void shouldReturnDeuceWhenBothPlayersReachForty() {
        GameResult result = scoring.computeScore("AAABBB");
        assertEquals('-', result.getWinner());
        assertTrue(result.getSequence().contains("Game not finished"));
    }

    @Test
    void shouldDeclareAWinnerAfterDeuceAndAdvantage() {
        // AAABBB → deuce, AA → A avantage puis victoire
        GameResult result = scoring.computeScore("AAABBBAA");
        assertEquals('A', result.getWinner());
    }

    @Test
    void shouldReturnGameNotFinishedWhenAdvantageIsLost() {
        GameResult result = scoring.computeScore("AAABBBBA");
        assertEquals('X', result.getWinner());
        assertTrue(result.getSequence().contains("Game not finished"));
    }

    @Test
    void shouldReturnGameNotFinishedAfterMultipleDeuceRoundtrips() {
        GameResult result = scoring.computeScore("AAABBBABBA");
        assertEquals('X', result.getWinner());
        assertTrue(result.getSequence().contains("Game not finished"));
    }

    @Test
    void shouldStopAtWinningBallAndIgnoreRemainingBalls() {
        // A gagne au 8ème point, les BBBB suivants doivent être ignorés
        GameResult result = scoring.computeScore("AAABBBAABBBB");
        assertEquals('A', result.getWinner());
        assertEquals("Player A wins the game", result.getSequence().get(result.getSequence().size() - 1));
    }

    @Test
    void shouldThrowExceptionForInvalidPlayer() {
        assertThrows(InvalidPlayerException.class,
            () -> scoring.computeScore("AABABC"));
    }

    @Test
    void shouldThrowExceptionForEmptyGame() {
        assertThrows(IllegalArgumentException.class,
            () -> scoring.computeScore(""));
    }

    @Test
    void shouldThrowExceptionForNullGame() {
        assertThrows(IllegalArgumentException.class,
            () -> scoring.computeScore(null));
    }
}
