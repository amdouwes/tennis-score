package org.smail.tennis.score;


/**
 * Calcule l'etat du jeu et le score en fonction de la nouvelle marquée par <player> 
 * le statut du jeu peut-etre 
 * - END  jeu terminé et emporté par un joueur
 * - DEUCE - égalité après un 40
 * - ADVANTAGE lorsqu'un joueur marque après DEUCE
 * - NORMAL sinon
 * Calcul des scores dans un tableau de ints representant des indices de score: 0:0,1:15,2:30;3:40
 * cela permet d'incrementer des indices sans compliquer le calcul à cause du 40 qui n'est 
 * pas un mutiple de 15
 * à tout instant Winner peut etre A,B,X ou - en cas de DEUCE
 */
public class TennisGame {

    enum GameState {
        NORMAL, DEUCE, ADVANTAGE, END
    }

    private static final int SCORE_AT_FORTY = 3;

    private final int[] score = new int[2]; // 0=0, 1=15, 2=30, 3=40
    private GameState state = GameState.NORMAL;
    private char winner = 'X'; // X=pas de gagnant, -=deuce

    public void play(char player) {
        if (isFinished()) return;
        int pi = player == 'A' ? 0 : 1;

        switch (state) {
            case NORMAL -> {
                if (score[pi] == SCORE_AT_FORTY) {
                    // déjà à 40 ==> victoire
                    state = GameState.END;
                    winner = player;
                } else {
                    score[pi]++;
                    if (score[0] == SCORE_AT_FORTY && score[1] == SCORE_AT_FORTY) {
                        state = GameState.DEUCE;
                        winner = '-';
                    }
                }
            }
            case DEUCE -> {
                state = GameState.ADVANTAGE;
                winner = player;
            }
            case ADVANTAGE -> {
                if (player == winner) {
                    state = GameState.END;
                } else {
                    // retour deuce
                    winner = 'X';
                    state = GameState.DEUCE;
                }
            }
            case END -> {}
        }
    }

    public boolean isFinished() {
        return state == GameState.END;
    }

    public char getWinner() {
        return winner;
    }

    public int[] getScore() {
        return score.clone();
    }
}
