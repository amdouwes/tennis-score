# Tennis Score Kata

## Description
Implementation of a simple tennis score computer in Java.
A method takes a String as input (ex: "ABABAA") where 'A' and 'B' represent players,
and prints the score after each ball and the winner of the game.

## Rules
- Each player starts at 0 points
- Points : 0 → 15 → 30 → 40
- If a player has 40 points and wins the ball, he wins the game
- If both players have 40 points : Deuce
- After Deuce : the winner of the ball gets Advantage
- If the player with Advantage wins : he wins the game
- If the player without Advantage wins : back to Deuce

## Game states — example sequence (AAABBBBABAAA)

```
Ball  1  A  →  15 / 0    NORMAL
Ball  2  A  →  30 / 0    NORMAL
Ball  3  A  →  40 / 0    NORMAL
Ball  4  B  →  40 / 15   NORMAL
Ball  5  B  →  40 / 30   NORMAL
Ball  6  B  →  40 / 40   DEUCE
Ball  7  A  →  40 / 40   ADVANTAGE A
Ball  8  B  →  40 / 40   DEUCE
Ball  9  B  →  40 / 40   ADVANTAGE B
Ball 10  A  →  40 / 40   DEUCE
Ball 11  A  →  40 / 40   ADVANTAGE A
Ball 12  A  →  40 / 40   END — A wins
```



## Technical choices
- `int[]  POINTS = {0, 15, 30, 40}` : score represented as an index to avoid arithmetic issues
- `enum GameState` : tracks the state of the game (NORMAL, DEUCE, ADVANTAGE)
- Single Responsibility : `TennisGame` handles the state machine, `TennisScoring` orchestrates and formats

## How to run
```bash
mvn compile
mvn test
```

### Run the demo (main)
```bash
mvn exec:java -Dexec.mainClass="org.smail.tennis.score.TennisScoring"
```

This will print a series of scored sequences covering all game scenarios: normal play, deuce, advantage, win, and edge cases. Example output:
```
Player A : 15 / Player B : 0
Player A : 15 / Player B : 15
Player A : 30 / Player B : 15
Player A : 30 / Player B : 30
Player A : 40 / Player B : 30
Player A : 40 / Player B : 40
Player A wins the game
```


```

## Stack
- Java 17
- JUnit 5
- Maven
