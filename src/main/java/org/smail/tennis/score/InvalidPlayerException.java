package org.smail.tennis.score;

public class InvalidPlayerException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	 
	
	public  InvalidPlayerException(char player) {
		super("invalid player name "+ player +", should be A or B");
		
	}



}
