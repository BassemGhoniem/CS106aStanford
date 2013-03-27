/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
	public void run(){
		if(frontIsBlocked()){//if the board is all about one column
			turnLeft();
			drawOneRow();
		}else drawBoard();
	}

	 
	private void drawBoard(){
		while(frontIsClear()){
			drawOneRow();
			moveToNextRow();
			
		}
}
	private void moveToNextRow() {
		if(facingEast()&&leftIsClear()){
			if(beepersPresent()){
				turnLeft();	
				move();
				turnLeft();
				//if the row ends with beeper then karel needs to move additional
				//step after moving to the next row, and begins checking
				move();
			}else{
				turnLeft();
				move();
				turnLeft();
			}
		}else if(facingWest()&&rightIsClear()){
			turnRight();
			move();
			turnRight();
		}
	}
/*
 * drawOneRow put one beeper in every two steps
 */
	private void drawOneRow() {
		putBeeper();
		while(frontIsClear()){
			move();
			if(frontIsClear()){
				move();
				putBeeper();
			}
		}
		
	}
}
