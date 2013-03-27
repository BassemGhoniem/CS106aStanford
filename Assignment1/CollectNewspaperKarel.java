/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	public void run(){
		moveToTheNewspaper();
		pickBeeper();
		returnHome();
	}

	private void moveToTheNewspaper() {
		moveToWall();
		turnRight();
		while(leftIsBlocked())
			move();
		turnLeft();
		move();		
	}

	
	private void returnHome() {
		turnAround();
		move();
		turnRight();
		moveToWall();
		turnLeft();
		moveToWall();
		turnAround();
	}
	private void moveToWall(){
		while(frontIsClear())
			move();
	}

}
