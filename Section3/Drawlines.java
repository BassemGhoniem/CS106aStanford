import java.awt.Color;
import java.awt.event.*;
import acm.program.*;
import acm.graphics.*;

public class Drawlines extends GraphicsProgram {
	public void run(){
		addMouseListeners();
	}
	public void mousePressed(MouseEvent e){
		line = new GLine(e.getX(),e.getY(),e.getX(),e.getY());
		add(line);
		line.setColor(Color.BLUE);
	}
	public void mouseDragged(MouseEvent e){
		line.setEndPoint(e.getX(), e.getY());
	}
	private GLine line;
}
