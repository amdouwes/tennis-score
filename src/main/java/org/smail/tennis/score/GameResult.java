/**
 * 
 */
package org.smail.tennis.score;

import java.util.List;

/**
 * contient la sequence des chaines de caractère de score instantané et le 
 * gagnant du jeu.
 */
public class GameResult {
	public GameResult(List<String> sequence, char winner) {
		super();
		this.sequence = sequence;
		this.winner = winner;
	}
	public List<String> getSequence() {
		return sequence;
	}
	public void setSequence(List<String> sequence) {
		this.sequence = sequence;
	}
	public char getWinner() {
		return winner;
	}
	public void setWinner(char winner) {
		this.winner = winner;
	}
	private List<String> sequence;
	private char winner;

}
