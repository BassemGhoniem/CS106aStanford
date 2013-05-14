/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		initCategories();
		for(int cat = 1;cat <= N_SCORING_CATEGORIES; cat++){
			
			//The initial roll
			for(int player = 0;player < nPlayers; player++ ){
				display.printMessage(playerNames[player] +"'s turn!, Click the \"Roll Dice\"button to roll the dice"  );
				display.waitForPlayerToClickRoll(player + 1);
				rollDice();
				display.displayDice(dice);
				
				// the Second and Third roll
				for(int reRoll = 0; reRoll < 2 ; reRoll++){
					display.printMessage("Select the dice you wish to re-roll and click\"Roll Again\"");
					display.waitForPlayerToSelectDice();
					reRollDice();
					display.displayDice(dice);
				}
				
				// In the end of the turn Selecting category
				display.printMessage("Select a category for this roll");
				
				int category = computeScore(player);
				int score = scores[player][category-1];
				
				// pass "player + 1 " instead of "player" cuz the methods take player from 1 to Nplayer 
				display.updateScorecard(category, player + 1, score); 
				
				//	computeTotalScores(player);
				display.updateScorecard(UPPER_BONUS , player + 1, scores[player][UPPER_BONUS-1]);
				display.updateScorecard(UPPER_SCORE , player + 1, scores[player][UPPER_SCORE-1]);
				display.updateScorecard(LOWER_SCORE , player + 1, scores[player][LOWER_SCORE-1]);
				display.updateScorecard(TOTAL , player + 1, scores[player][TOTAL-1]);
				
			}
		}
			
		// choosing the winner 
		int maxTotal = scores[0][TOTAL-1];
		String winner = playerNames[0];
		for(int player = 0; player < nPlayers ; player++){
			if(scores[player][TOTAL-1] > maxTotal){
				maxTotal = scores[player][TOTAL-1];
				winner = playerNames[player];
			}
		}
		display.printMessage("Congratulation, " + winner + ", you're the winner with a total score of " + maxTotal +"!");
		
	}
	
	
	private int computeScore( int player) {
			int category = display.waitForPlayerToSelectCategory();
			while (YahtzeeMagicStub.checkCategory(dice, category) && scores[player][category - 1] != -1){
				display.printMessage("You alreadt picked that category. Please choose another category");
				category = display.waitForPlayerToSelectCategory();
			}
			
			if(YahtzeeMagicStub.checkCategory(dice, category)){
				scoreForThisCategory(category , player);
			}else{
					scores[player][category - 1] = 0;	
			}		
			
			if(scores[player][UPPER_SCORE-1] >= 63)scores[player][UPPER_BONUS-1] = 35 ;
			scores[player][TOTAL-1] = scores[player][UPPER_SCORE-1] + scores[player][UPPER_BONUS-1] + scores[player][LOWER_SCORE-1];
			
			return category;
	}
	
	private void scoreForThisCategory(int category, int player) {
		int cat = category - 1; // to store the category x in the index x-1 in the scores array
		
		switch(category){
		case ONES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 1)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;			
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case TWOS:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 2)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case THREES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 3)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case FOURS:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 4)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case FIVES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 5)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case SIXES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 6)scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][UPPER_SCORE-1]+= scores[player][cat];
			break;
		case THREE_OF_A_KIND:case FOUR_OF_A_KIND:case CHANCE:
			for(int dc = 0; dc< N_DICE;dc++)
				scores[player][cat] += dice[dc];
			scores[player][cat]++ ;
			scores[player][LOWER_SCORE-1]+= scores[player][cat];
			break;			
		case FULL_HOUSE:
			scores[player][cat]=25;
			scores[player][LOWER_SCORE-1]+= scores[player][cat];
			break;
		case SMALL_STRAIGHT:
			scores[player][cat]=30;
			scores[player][LOWER_SCORE-1]+= scores[player][cat];
			break;
		case LARGE_STRAIGHT:
			scores[player][cat]=40;
			scores[player][LOWER_SCORE-1]+= scores[player][cat];
			break;
		case YAHTZEE:
			scores[player][cat]=50;
			scores[player][LOWER_SCORE-1]+= scores[player][cat];
			break;						
		}
		
	}

	private void reRollDice() {
			for(int dc = 0; dc< N_DICE;dc++){
				if(display.isDieSelected(dc))
					dice[dc] = rgen.nextInt(1, 6);
			}			
		}
	
	private void rollDice() {
			for(int dc = 0; dc< N_DICE;dc++){
				dice[dc] = rgen.nextInt(1, 6);
			}			
		}
	private void initCategories(){
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < scores[0].length; j++){
				scores[i][j] = -1;
			}
			scores[i][UPPER_SCORE-1]=0;
			scores[i][LOWER_SCORE-1]=0;
			scores[i][UPPER_BONUS-1]=0;
			scores[i][TOTAL-1]=0;
		}
	}

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[] dice = new int[N_DICE];
	private int[][] scores = new int[MAX_PLAYERS][N_CATEGORIES];

}
