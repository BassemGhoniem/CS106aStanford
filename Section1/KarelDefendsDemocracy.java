/*
 * File: KarelDefendsDemocracy
 * ---------------------
 * this is the solution of the first section in CS106A
 */


import stanford.karel.*;

public class KarelDefendsDemocracy extends SuperKarel {
	public void run(){
		while(frontIsClear()){
			move();
			checkTheRectangle();
		}
	}
	/*
	 * checkTheRectangle method check either the rectangle is punched or not 
	 * if the rectangle is bunched clean the remaining Pieces of it 
	 * precondition: karel is before the rectangle at the door(the hole)
	 * postcondition: karel is out of the the Rectangle 
	 */
	private void checkTheRectangle() {
		if(noBeepersPresent()){
			turnLeft();
			cleanTheCorner();
			cleanTheCorner();
			turnRight();
		}
		move();
	}
	/*
	 * cleanTheCorner method check the corner if there is Beeper on it
	 * it pick all of them
	 * precondition: Karel Facing to the corner which we want clean it
	 * postCondition : karel Facing to the opposite corner
	 */
	private void cleanTheCorner() {
		move();
		while(beepersPresent())
			pickBeeper();
		turnAround();
		move();
	}
}