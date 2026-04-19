package org.smail.tennis.score;

import java.util.ArrayList;
import java.util.List;


/**
 * execute un jeu contenant une suite de caracères A ou B, indiquant le gagnant de chaque belle
 * utilise la classe TennisGame qui contient la logique métier pour chaque balle
 */
public class TennisScoring {

	private static final int[] POINTS = { 0, 15, 30, 40 };

	GameResult computeScore(String game) {
		if (game == null || game.length() == 0)
			throw new IllegalArgumentException(game);
		if (!game.matches("[AB]+"))
			throw new InvalidPlayerException(game);

		List<String> sequence = new ArrayList<String>();
		TennisGame tennisGame = new TennisGame();

		for (char player : game.toCharArray()) {
			tennisGame.play(player);
			sequence.add(formatScore(tennisGame.getScore()));

			if (tennisGame.isFinished()) {
				sequence.add("Player " + tennisGame.getWinner() + " wins the game");
				return new GameResult(sequence, tennisGame.getWinner());
			}
		}

		sequence.add("Game not finished");
		return new GameResult(sequence, tennisGame.getWinner());
	}

	private String formatScore(int[] score) {
		return "Player A : " + POINTS[score[0]] + " / Player B : " + POINTS[score[1]];
	}

	/*
	 * game : a suite of chars like ABABAA describing scoring sequence, where A
	 * and B are players. print who won the game and it's sequence print the score
	 * after each won ball (for example : "Player A : 15 / Player B : 30") and print
	 * the winner of the game
	 */
	public void printScore(String game) {
		GameResult res = this.computeScore(game);
		for (String line : res.getSequence()) {
			System.out.println(line);
		}
	}

	// point d'entrée pour démonstration, les TU couvrent la logique
	public static void main(String[] args) {
		TennisScoring scoring = new TennisScoring();

		scoring.printScore("AAB");          // partie non terminée
		scoring.printScore("ABBA");         // partie en cours
		scoring.printScore("AAAA");         // A gagne sans que B marque
		scoring.printScore("BBBB");         // B gagne sans que A marque
		scoring.printScore("ABABAA");       // exemple de l'énoncé, A gagne
		scoring.printScore("BABABB");       // B gagne
		scoring.printScore("AAABBB");       // deuce
		scoring.printScore("AAABBBAA");     // deuce, A avantage et gagne
		scoring.printScore("AAABBBBA");     // deuce, B avantage puis retour deuce
		scoring.printScore("AAABBBABBA");   // plusieurs deuces, partie non terminée
		scoring.printScore("AAABBBAABBBB"); // A gagne au 8ème point, balles suivantes ignorées

		try { scoring.printScore("AABABC"); } // caractère invalide
		catch (InvalidPlayerException e) { System.out.println(e.getMessage()); }

		try { scoring.printScore(""); }      // chaîne vide
		catch (IllegalArgumentException e) { System.out.println("empty game"); }

		try { scoring.printScore(null); }    // null
		catch (IllegalArgumentException e) { System.out.println("null game"); }
	}
}
