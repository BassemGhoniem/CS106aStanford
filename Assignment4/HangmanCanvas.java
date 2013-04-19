/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	public String errorChars = "";

/** Resets the display so that only the scaffold appears */
	public void reset() {
		scaffold();
		beam();
		rope();		
	}

	private void rope() {
		double ropeX = getWidth()/2;
		double ropeY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3;
		double ropeY2 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH;
		GLine rope = new GLine(ropeX, ropeY1, ropeX, ropeY2);
		add(rope);
	}
	
	private void beam() {
		double beamY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3;
		double beamX1 = getWidth()/2-BEAM_LENGTH;
		double beamX2 = getWidth()/2;
		GLine beam = new GLine(beamX1, beamY, beamX2, beamY);
		add(beam);
	}
	
	private void scaffold() {
		double scaffoldStartY = getHeight() / 2 + SCAFFOLD_HEIGHT / 3;
		double scaffoldEndY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3;
		double scaffoldX = getWidth()/2-BEAM_LENGTH;
		GLine scaffold = new GLine(scaffoldX, scaffoldStartY, scaffoldX, scaffoldEndY);
		add(scaffold);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		
		double x = getWidth() / 6;
		double y = 5 * getHeight() / 6;
		
		if(getElementAt(x,y) != null)
			remove(getElementAt(x,y));
		
		GLabel dashedWord = new GLabel(word);
		dashedWord.setFont("SansSerif-italic-30");
		add(dashedWord,x,y);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char guessedLetter) {
		errorChars += guessedLetter;
		double x = getWidth() / 6;
		double y = getHeight()-30 ;
		
		if(getElementAt(x,y) != null)
			remove(getElementAt(x,y));
		
		GLabel errorletters = new GLabel(errorChars);
		errorletters.setFont("SansSerif-20");
		add(errorletters,x,y);
		
		switch(errorChars.length()){
			case 1 : head(); 		break;
			case 2 : body(); 		break;
			case 3 : leftArm(); 	break;
			case 4 : rightArm(); 	break;
			case 5 : leftLeg(); 	break;
			case 6 : rightLeg();	break;
			case 7 : leftFoot();	break;
			case 8 : rightFoot();			
		}
	}

	private void rightFoot() {
		double rightFootX0 = getWidth() / 2 + HIP_WIDTH ;
		double rightFootX1 = getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH;
		double rightFootY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine rightFoot = new GLine(rightFootX0, rightFootY, rightFootX1, rightFootY);
		add(rightFoot);
	}
	
	private void leftFoot() {
		double leftFootX0 = getWidth() / 2 - HIP_WIDTH ;
		double leftFootX1 = getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH;
		double leftFootY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine leftFoot = new GLine(leftFootX0, leftFootY, leftFootX1, leftFootY);
		add(leftFoot);
	}
	
	private void rightLeg() {
		double rightUpperLegX0 = getWidth() / 2;
		double rightUpperLegX1 = getWidth() / 2 + HIP_WIDTH ;
		double rightUpperLegY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH;
		GLine rightUpperLeg = new GLine(rightUpperLegX0, rightUpperLegY, rightUpperLegX1, rightUpperLegY);
		add(rightUpperLeg);
		
		double rightLowerLegX = getWidth() / 2 + HIP_WIDTH ;
		double rightLowerLegY0 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH;
		double rightLowerLegY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine rightLowerLeg = new GLine(rightLowerLegX, rightLowerLegY0, rightLowerLegX, rightLowerLegY1);
		add(rightLowerLeg);
	}
	
	private void leftLeg() {
		double leftUpperLegX0 = getWidth() / 2;
		double leftUpperLegX1 = getWidth() / 2 - HIP_WIDTH ;
		double leftUpperLegY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH;
		GLine leftUpperLeg = new GLine(leftUpperLegX0, leftUpperLegY, leftUpperLegX1, leftUpperLegY);
		add(leftUpperLeg);
		
		double leftLowerLegX = getWidth() / 2 - HIP_WIDTH ;
		double leftLowerLegY0 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH;
		double leftLowerLegY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine leftLowerLeg = new GLine(leftLowerLegX, leftLowerLegY0, leftLowerLegX, leftLowerLegY1);
		add(leftLowerLeg);
	}
	
	private void rightArm() {
		double rightUpperArmX0 = getWidth() / 2;
		double rightUpperArmX1 = getWidth() / 2 + UPPER_ARM_LENGTH ;
		double rightUpperArmY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine rightUpperArm = new GLine(rightUpperArmX0, rightUpperArmY, rightUpperArmX1, rightUpperArmY);
		add(rightUpperArm);
		
		double rightLowerArmX = getWidth() / 2 + UPPER_ARM_LENGTH ;
		double rightLowerArmY0 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double rightLowerArmY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
		GLine rightLowerArm = new GLine(rightLowerArmX, rightLowerArmY0, rightLowerArmX, rightLowerArmY1);
		add(rightLowerArm);
	}
	
	private void leftArm() {
		double leftUpperArmX0 = getWidth() / 2;
		double leftUpperArmX1 = getWidth() / 2 - UPPER_ARM_LENGTH ;
		double leftUpperArmY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine leftUpperArm = new GLine(leftUpperArmX0, leftUpperArmY, leftUpperArmX1, leftUpperArmY);
		add(leftUpperArm);
		
		double leftLowerArmX = getWidth() / 2 - UPPER_ARM_LENGTH ;
		double leftLowerArmY0 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		double leftLowerArmY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH;
		GLine leftLowerArm = new GLine(leftLowerArmX, leftLowerArmY0, leftLowerArmX, leftLowerArmY1);
		add(leftLowerArm);
	}
	
	private void body() {
		double bodyY0 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS ;
		double bodyY1 = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH + HEAD_RADIUS + BODY_LENGTH;
		double bodyX = getWidth() / 2;
		GLine body = new GLine(bodyX, bodyY0, bodyX, bodyY1);
		add(body);
	}
	
	private void head() {
		GOval head = new GOval(HEAD_RADIUS, HEAD_RADIUS );
		double headX = (getWidth() - HEAD_RADIUS) / 2;
		double headY = getHeight() / 2 - 2 * SCAFFOLD_HEIGHT / 3 + ROPE_LENGTH;
		add(head,headX,headY);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 310;
	private static final int BEAM_LENGTH = 114;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 78;
	private static final int FOOT_LENGTH = 28;

}
