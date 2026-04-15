package org.smail.tennis.score;

public class TennisScoring {

	enum GameState {
		NORMAL, DEUCE, ADVANTAGE
	}

	
	
	private static final int[] POINTS = { 0, 15, 30, 40 };
	

	/*
	 * game : a suite of characters like ABABAA describing scoring sequence, where A
	 * and B are players print who won the game and it's sequence print the score
	 * after each won ball (for example : “Player A : 15 / Player B : 30”) and print
	 * the winner of the game
	 */
	public void printScore(String game) {

		if (game==null || game.length()==0)
			throw new IllegalArgumentException(game);
		int scoreAIndex = 0;
		int scoreBIndex = 0;
		GameState state = GameState.NORMAL;
		char winner = 'X';
		for (int i = 0; i < game.length(); i++) {
			char player = game.charAt(i);
			if (player != 'A' && player != 'B')
				throw new InvalidPlayerException(player);

			switch (state) {
			case NORMAL -> {

				if (player == 'A') {
					if (scoreAIndex == 3 && scoreBIndex == 3) {
						state = GameState.DEUCE;
					} else if (scoreAIndex == 3) {
						winner = 'A';
						System.out.println("Player " + winner + " :  Wins the game");
						return;
					} else {
						scoreAIndex++;
					}
				}
				if (player == 'B') {
					if (scoreBIndex == 3 && scoreAIndex == 3) {
						state = GameState.DEUCE;
					} else if (scoreBIndex == 3) {
						winner = 'B';
						System.out.println("Player " + winner + " :  Wins the game");
						return;
					} else {
						scoreBIndex++;
					}
				}
			}

			case DEUCE -> {
				if (player == 'A') {
					state = GameState.ADVANTAGE;
					winner = 'A';
				} else if (player == 'B') {
					state = GameState.ADVANTAGE;
					winner = 'B';
				}
			}

			case ADVANTAGE -> {
				if (player == 'A') {
					winner = 'A';
					return;
				} else if (player == 'B') {
					winner = 'B';
					return;
				}
			}

			}
			System.out.println("Player A : " + POINTS[scoreAIndex] + "/ Player B : " + POINTS[scoreBIndex]);

		}
		System.out.println("Player " + winner + " :  Wins the game \n\n");

	}

	public static void main(String[] args) {
		TennisScoring scoring = new TennisScoring();
		scoring.printScore("AAAA"); // A gagne sans que B marque un point
		scoring.printScore("BBBB"); // B gagne sans que A marque un poin
		scoring.printScore("ABABAA"); // A Gagne
		scoring.printScore("BABABB"); // A Gagne
		scoring.printScore("AAABBBAA");    // deuce, A prend l'avantage et gagne
		scoring.printScore("AAABBBBA");    // deuce, B prend l'avantage et gagne
		scoring.printScore("AAABBBABBA");  // deuce, A avantage, retour deuce, B avantage, B gagne
		scoring.printScore("AAABBBABABAA");// plusieurs allers-retours deuce/avantage
		scoring.printScore("AABABC");      // caractère invalide, doit lever InvalidPlayerException
		scoring.printScore("");            // chaîne vide
		scoring.printScore(null);          // null, que se passe-t-il ?
		

	}
}
