/*
 * File: ProgramHierarchy.java
 * Name:
 * Section Leader:
 * ==========================================================
 * Replace these comments with a description of your program.
 * Since this program is more freeform than the rest, tell us
 * a bit about it in these comments!
 */
import acm.program.*;
import acm.graphics.*;
public class ProgramHierarchy extends GraphicsProgram {
	private static final double BOX_WIDTH = 72 * 3;
	private static final double BOX_HEIGHT = 72 *1;
	private static final double SEP = 72 * 0.4;
	public void run() {
		
		GRect program = new GRect(getWidth()/2-BOX_WIDTH/2,getHeight()/2-1.5*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
		add(program);
		GLabel programLabel = new GLabel("Program");
		add(programLabel,getWidth()/2-programLabel.getWidth()/2,getHeight()/2-BOX_HEIGHT+programLabel.getAscent()/2);
		
		
		GRect console = new GRect(getWidth()/2-BOX_WIDTH/2,getHeight()/2+0.5*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
		add(console);
		GLabel consoleLabel = new GLabel("ConsoleProgram");
		add(consoleLabel,getWidth()/2-consoleLabel.getWidth()/2,getHeight()/2+BOX_HEIGHT+consoleLabel.getAscent()/2);
		
		
		GRect graphics = new GRect(getWidth()/2-3*BOX_WIDTH/2-SEP,getHeight()/2+0.5*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
		add(graphics);
		GLabel graphicsLabel = new GLabel("GraphicsProgram");
		add(graphicsLabel,getWidth()/2-BOX_WIDTH-SEP-graphicsLabel.getWidth()/2,getHeight()/2+BOX_HEIGHT+graphicsLabel.getAscent()/2);
		
		
		GRect dialog = new GRect(getWidth()/2+BOX_WIDTH/2+SEP,getHeight()/2+0.5*BOX_HEIGHT,BOX_WIDTH,BOX_HEIGHT);
		add(dialog);
		GLabel dialogLabel = new GLabel("DialogProgram");
		add(dialogLabel,getWidth()/2+BOX_WIDTH+SEP-dialogLabel.getWidth()/2,getHeight()/2+BOX_HEIGHT+dialogLabel.getAscent()/2);
		
		
		GLine line1 = new GLine(getWidth()/2,getHeight()/2-0.5*BOX_HEIGHT,getWidth()/2,getHeight()/2 + 0.5 * BOX_HEIGHT);
		add(line1);
		
		GLine line2 = new GLine(getWidth()/2,getHeight()/2-0.5*BOX_HEIGHT,getWidth()/2-BOX_WIDTH-SEP,getHeight()/2 + 0.5 * BOX_HEIGHT);
		add(line2);
		
		GLine line3 = new GLine(getWidth()/2,getHeight()/2-0.5*BOX_HEIGHT,getWidth()/2+BOX_WIDTH+SEP,getHeight()/2 + 0.5 * BOX_HEIGHT);
		add(line3);
	}
}
