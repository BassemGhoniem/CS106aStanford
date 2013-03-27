/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {
	public void run(){
		while(frontIsClear()){
			repairColumn();
			moveToNextCoulmn();
		}
		repairColumn();
	}

	/*
	 * this method repair the column by check for the missed stones
	 * and put a beeper in their places and back to the floor again
	 * pre-conditions : karel facing east on the bottom of the column
	 * post-conditions : karel facing east on the bottom of the repaired column
	 */
	private void repairColumn() {
		turnLeft();
		while(frontIsClear()){
			if(noBeepersPresent())
				putBeeper();
			move();
		}
		if(noBeepersPresent())
			putBeeper();
		turnAround();
		moveToWall();
		turnLeft();
	}

	private void moveToWall() {
		while(frontIsClear())
			move();
	}

	private void moveToNextCoulmn() {
		for(int i=0; i<4;i++)
			move();
	}
}
