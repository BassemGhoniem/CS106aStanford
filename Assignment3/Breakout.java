/*
 *this program create the break out game 
 *which consist of a board has 10 raws and each row has 10 bricks 
 *and there is ball and paddle if the ball hit a brick it disappear
 *from the board and if the ball hit the paddle or any side except
 *the bottom side it bounce if the all bricks removed from the board
 *you win! if the ball go away from the paddle and go to the bottom
 *you have two additional turns 
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
/*
 * the set of constants used in the design of the game
 */
/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

	private static  double vy = 3;
/** the paddle speed */
	private static final int PADDLE_SPEED=10;
/** the delay time between each move execution */	
	private static final int DELAY = 6;

	public void run() {
		
		setUp();
		addMouseListeners();
	
		for(int i =0; i< NTURNS;i++){
			playGame();
			if(BRICKS_ON_CANVAS==0){
				printWinner();break;
			}
			else ball();
		}
		if(BRICKS_ON_CANVAS!=0)	printGameOver();
	}
		
	private void printWinner() {
		removeAll();
		GLabel win = new GLabel("YOU WIN !");
		win.setFont("Monospaced-60");
		add(win,WIDTH/2-win.getWidth()/2,HEIGHT/2- win.getAscent()/2);
		setBackground(Color.GREEN);
	}
	
	
	private void printGameOver() {
		removeAll();
		GLabel lose= new GLabel("GAMEOVER!");
		lose.setFont("Monospaced-60");
		add(lose,WIDTH/2-lose.getWidth()/2,HEIGHT/2-lose.getAscent()/2);
		setBackground(Color.RED);
	}


	private void playGame(){
		waitForClick();
		newVelocity();
		while(isPlaying())
		moveBall();
	}
/*
 * isPlaying is a predicate method to determine when the game end
 * you can whenever the ball on the canvas and there is bricks
 */
	private boolean isPlaying() {
		if(BRICKS_ON_CANVAS!=0)
		return (ball.getY() - ball.getHeight()-PADDLE_Y_OFFSET < getHeight());
		
		return false;
	}

/*
 * new velocity is a method to change the velocity and direction of 
 * the ball each time you play the game
 */
 
	private void newVelocity(){
		vx =rgen.nextDouble(1.0,3.0);
		if(rgen.nextBoolean(0.5)) vx =-vx;
	}
	private void moveBall() {
		ball.move(vx, vy);
		//change the direction of the ball whenever hit any side except the bottom
		if(ball.getX()>= WIDTH - 2 *BALL_RADIUS || ball.getX()<=0)vx = -vx;
		else if(ball.getY() < 0)vy = -vy;	
		
		GObject collider = getColliidingObject();
		AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
		if(collider!=null){
			
			if(collider==paddle)vy = -vy;
			else{
				bounceClip.play();
				remove(collider);
				vy = -vy;
				BRICKS_ON_CANVAS--;
			}
		}
		pause(DELAY);
	}
	
	// this method return the object that the ball hit it
	private	GObject	getColliidingObject(){
       if(getElementAt(ball.getX(),ball.getY())!=null){
    	   return( getElementAt(ball.getX(),ball.getY()));
    	   
       }else if(getElementAt(ball.getX() + 2 * BALL_RADIUS , ball.getY())!=null){
    	   return getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY());
    	   
       }else if(getElementAt(ball.getX(),ball.getY()+2 * BALL_RADIUS)!=null){
    	   return getElementAt(ball.getX(),ball.getY()+2 * BALL_RADIUS);
    	   
       }else if(getElementAt(ball.getX()+ 2 * BALL_RADIUS,ball.getY() + 2 * BALL_RADIUS)!=null){
    	   return getElementAt(ball.getX()+ 2 * BALL_RADIUS,ball.getY() + 2 * BALL_RADIUS);
       }else
    	   return null;
	}
    		   

	
	
	
	
	private void setUp(){
		buildBricksBlock();
		ball();
		paddle();
		
	}
	private void paddle() {
		paddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle,WIDTH / 2 - PADDLE_WIDTH / 2,getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
	}


	private void buildBricksBlock() {
		for(int row = 0;row < NBRICK_ROWS;row++ ){
			//Compute y for the row of bricks 
			int y = (row + 1) * BRICK_HEIGHT + BRICK_Y_OFFSET + row * BRICK_SEP;
			//build row of bricks
			for(int i=0;i<NBRICKS_PER_ROW;i++){
				int x = WIDTH/2 -NBRICKS_PER_ROW * BRICK_WIDTH/2-(NBRICKS_PER_ROW - 1) * BRICK_SEP/2+i*BRICK_WIDTH +i * BRICK_SEP;
				GRect brick = new GRect(x,y,BRICK_WIDTH,BRICK_HEIGHT);
				brick.setFilled(true);				
				add(brick);
				//determine the color of the block relative to the number of the row
				switch(row){
					case 0 : case 1 : brick.setColor(Color.RED);     break;
					case 2 : case 3 : brick.setColor(Color.ORANGE);  break;
					case 4 : case 5 : brick.setColor(Color.YELLOW);  break;
					case 6 : case 7 : brick.setColor(Color.GREEN);   break;
					case 8 : case 9 : brick.setColor(Color.CYAN);    break;
				}
			}
		}
	}


	private void ball() {
		ball = new GOval(BALL_RADIUS,BALL_RADIUS);
		ball.setFilled(true);
		add(ball,WIDTH/2-BALL_RADIUS/2,HEIGHT/2-BALL_RADIUS/2);
	}


	public void mouseMoved(MouseEvent e){
		while(e.getX()>paddle.getX()&&paddle.getX()+PADDLE_WIDTH<WIDTH)
			paddle.move(PADDLE_SPEED, 0);
		while(e.getX()<paddle.getX()&&paddle.getX()>0)
			paddle.move(-PADDLE_SPEED, 0);
	}
	
	/*
	 * the set of the instance variable in the program 
	 */
	/** the numbers of the Bricks on the canvas to count the bricks */
	private int BRICKS_ON_CANVAS = NBRICKS_PER_ROW * NBRICK_ROWS;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	private GOval ball;
	private GRect paddle;
	
	private double vx;
	
}
