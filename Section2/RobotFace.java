import java.awt.*;

import acm.program.*;
import acm.graphics.*;
public class RobotFace extends GraphicsProgram {
	private static final double FACE_HEIGHT = 5.5 * 72;
	private static final double FACE_WEIDTH = 3.5 * 72;
	public void run(){
		double xc = getWidth()/2;
		double yc = getHeight()/2;
		
		GRect face = new GRect(FACE_WEIDTH,FACE_HEIGHT);
		face.setFilled(true);
		face.setFillColor(Color.GRAY);
		add(face,xc-face.getWidth()/2,yc-face.getHeight()/2);
		
		GOval lEye= new GOval(FACE_WEIDTH/5,FACE_WEIDTH/5);
		lEye.setFilled(true);
		lEye.setColor(Color.ORANGE);
		add(lEye,xc-FACE_WEIDTH/3,yc-FACE_HEIGHT/3);
		
		GOval rEye= new GOval(FACE_WEIDTH/5,FACE_WEIDTH/5);
		rEye.setFilled(true);
		rEye.setColor(Color.ORANGE);
		add(rEye,xc+FACE_WEIDTH/6,yc-FACE_HEIGHT/3);
		
		GRect mouth = new GRect(2*FACE_WEIDTH/3,FACE_HEIGHT/7);
		mouth.setFilled(true);
		mouth.setColor(Color.WHITE);
		add(mouth,xc-mouth.getWidth()/2,yc+FACE_HEIGHT/5);
		
		GRect nose = new GRect(FACE_WEIDTH/10,FACE_HEIGHT/7);
		nose.setFilled(true);
		nose.setColor(Color.BLACK);
		add(nose,xc-nose.getWidth()/2,yc-nose.getHeight()/2);
	}
}
