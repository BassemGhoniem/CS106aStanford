/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		while(nPlayers > MAX_PLAYERS){
			nPlayers = dialog.readInt("Enter number of players, The Max players is Four");			
		}
		playerNames = new String[nPlayers];
		scores = new int[nPlayers][N_CATEGORIES];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		initCategories();
		for(int turn = 0;turn < N_SCORING_CATEGORIES; turn++){
			
			//The initial roll
			for(int player = 0;player < nPlayers; player++ ){
				display.printMessage(playerNames[player] +"'s turn!, Click the \"Roll Dice\"button to roll the dice"  );
				display.waitForPlayerToClickRoll(player + 1);//The player number (between 1 and nPlayers)
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
				
				int category = getCategory(player);
				int score = scores[player][category];
				/*

				Usage: 	display.updateScorecard(category, player, score); 
				Parameters: 	Updates a value on the Yahtzee scorecard
					category  	The category number to update (between 1 and 17)
					player  	The player number (between 1 and nPlayers)
					score  	The score to display in that box
				*/
 
				display.updateScorecard(category + 1, player + 1, score); 
				display.updateScorecard(UPPER_BONUS + 1 , player + 1, scores[player][UPPER_BONUS]);
				display.updateScorecard(UPPER_SCORE + 1 , player + 1, scores[player][UPPER_SCORE]);
				display.updateScorecard(LOWER_SCORE + 1 , player + 1, scores[player][LOWER_SCORE]);
				display.updateScorecard(TOTAL + 1 , player + 1, scores[player][TOTAL]);
				
			}
		}
			
		// choosing the winner 
		int maxTotal = scores[0][TOTAL];
		String winner = playerNames[0];
		for(int player = 0; player < nPlayers ; player++){
			if(scores[player][TOTAL] > maxTotal){
				maxTotal = scores[player][TOTAL];
				winner = playerNames[player];
			}
		}
		display.printMessage("Congratulation, " + winner + ", you're the winner with a total score of " + maxTotal + " !");
		
	}
	/*
	 * getCategory method returns the chosen category with the condition that 
	 * the category did't taken before that time 
	 */
	
	private int getCategory( int player) {
			int category = display.waitForPlayerToSelectCategory() - 1;
			while (scores[player][category] != -1){
				display.printMessage("You alreadt picked that category. Please choose another category");
				category = display.waitForPlayerToSelectCategory() - 1;
			}
			
			if(checkCategory(dice, category)){
				scoreForThisCategory(category , player);
			}else{
					scores[player][category] = 0;	
			}			
			return category;
	}
	
	/*
	 * CheckCategory method takes the dice array and the category and return 
	 * a boolean value indicating whether the dice configuration is suitable 
	 * for that category or not 
	 */
	
	
	
	private boolean checkCategory(int[] dice, int category){
		int num = 0;
		int flag =0;
		Arrays.sort(dice);//sorting the dice array before any computations 
		
		switch(category){
		case YAHTZEE : 
			for(int dc = 0;dc < N_DICE;dc++){
				if(dice[dc] != dice[0])return false;
			}
			return true;
			
		case FOUR_OF_A_KIND:			
			for(int dc = 0;dc < N_DICE; dc++){
				num = dice[dc];
				flag = 0;
				for(int i = 0; i < N_DICE;i++){
					if(dice[i]==num)flag++;
				}
				if(flag >= 4)return true;
			}
			return false;
			
		case THREE_OF_A_KIND:			
			for(int dc = 0;dc < N_DICE; dc++){
				num = dice[dc];
				flag = 0;
				for(int i = 0; i < N_DICE;i++){
					if(dice[i]==num)flag++;
				}
				if(flag >= 3)return true;
			}
			return false;
			
		case FULL_HOUSE :			
			boolean p = (dice[0]==dice[1]) & (dice[N_DICE-2]==dice[N_DICE-1])
				& ((dice[2]==dice[1]) | (dice[2]==dice[N_DICE-1]));			
			if(p)return true;
			else return false;
			
		case LARGE_STRAIGHT :
			for(int dc = 0; dc <N_DICE-1; dc++){
				if(dice[dc+1] - dice[dc] != 1)return false;
			}
			return true;
			
		case SMALL_STRAIGHT:
			flag = 0;
			for(int dc = 0; dc <N_DICE-1; dc++){
				if(dice[dc+1] - dice[dc] != 1)flag++;
			}
			if(flag == 1)return true;
			else return false;
		}
				
		return true;
	}
	/*
	 * scoreForThisCategory method is used to update the category array according
	 * to the selected category and to compute the other fields like Total, lower,
	 * and upper score 
	 */
	private void scoreForThisCategory(int category, int player) {
		
		switch(category){
		case ONES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 1)scores[player][category] += dice[dc];
			scores[player][category]++ ;			
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case TWOS:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 2)scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case THREES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 3)scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case FOURS:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 4)scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case FIVES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 5)scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case SIXES:
			for(int dc = 0; dc< N_DICE;dc++)
				if(dice[dc] == 6)scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][UPPER_SCORE]+= scores[player][category];
			break;
		case THREE_OF_A_KIND:case FOUR_OF_A_KIND:case CHANCE:
			for(int dc = 0; dc< N_DICE;dc++)
				scores[player][category] += dice[dc];
			scores[player][category]++ ;
			scores[player][LOWER_SCORE]+= scores[player][category];
			break;			
		case FULL_HOUSE:
			scores[player][category]=25;
			scores[player][LOWER_SCORE]+= scores[player][category];
			break;
		case SMALL_STRAIGHT:
			scores[player][category]=30;
			scores[player][LOWER_SCORE]+= scores[player][category];
			break;
		case LARGE_STRAIGHT:
			scores[player][category]=40;
			scores[player][LOWER_SCORE]+= scores[player][category];
			break;
		case YAHTZEE:
			scores[player][category]=50;
			scores[player][LOWER_SCORE]+= scores[player][category];
			break;						
		}
		if(scores[player][UPPER_SCORE] >= 63) scores[player][UPPER_BONUS] = 35 ;
		scores[player][TOTAL] = scores[player][UPPER_SCORE] + scores[player][UPPER_BONUS] + scores[player][LOWER_SCORE];
		
	}
	/*
	 * re-roll dice method update the selected dices in the dice array 
	 */
	private void reRollDice() {
			for(int dc = 0; dc< N_DICE;dc++){
				if(display.isDieSelected(dc))
					dice[dc] = rgen.nextInt(1, 6);
			}			
		}
	/*
	 * roll Dice method updates the dice array
	 */
	private void rollDice() {
			for(int dc = 0; dc< N_DICE;dc++){
				dice[dc] = rgen.nextInt(1, 6);
			}			
		}
	/*
	 * initialization of the Categories array with scores of -1
	 * to enable us to know if the category used before or not
	 * if the category didn't use yet, its value'd be -1
	 */
	
	private void initCategories(){
		for(int i = 0; i < scores.length; i++){
			for(int j = 0; j < scores[0].length; j++){
				scores[i][j] = -1;
			}
			scores[i][UPPER_SCORE]=0;
			scores[i][LOWER_SCORE]=0;
			scores[i][UPPER_BONUS]=0;
			scores[i][TOTAL]=0;
		}
	}

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] dice = new int[N_DICE];
	private int[][] scores ;

}
