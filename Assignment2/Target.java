/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		add(GCircle(getWidth()/2,getHeight()/2,outerRadius ,Color.red));
		add(GCircle(getWidth()/2,getHeight()/2,ineerRadius ,Color.white));
		add(GCircle(getWidth()/2,getHeight()/2,smallerRadius ,Color.red));
		}
	private GOval GCircle(double x,double y,double r,Color color){
		GOval Circle = new GOval(x-r,y-r,2*r,2*r);
		Circle.setColor(color);
		Circle.setFilled( true);
		return Circle;
	}
	private static final double outerRadius = 72.0;
	private static final double ineerRadius = 0.65 * 72.0;
	private static final double smallerRadius = 0.3 * 72.0 ;
}
