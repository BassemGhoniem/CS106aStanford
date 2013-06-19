/*
 * File: BoxDiagram.java
 * ---------------------
 * 
 */

import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class BoxDiagram extends GraphicsProgram {
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	
	public void init() {
		addMouseListeners();
		add(new JLabel("Name"), SOUTH);
		name = new JTextField(30);
		name.addActionListener(this);
		name.setActionCommand("Add");
		add(name, SOUTH);
		
		add(new JButton("Add"), SOUTH);
		add(new JButton("Remove"), SOUTH);
		add(new JButton("Clear"), SOUTH);
		addActionListeners();
		
	}
	
	
	public void actionPerformed(ActionEvent e){
		String cmd = e.getActionCommand();
		if(cmd.equals("Clear")){
			for(String box :boxes.keySet())
				remove(boxes.get(box));
			boxes.clear();
			
		}else if(cmd.equals("Add")){
			String label = name.getText();
			 GCompound box = createLabledBox(label);
			boxes.put(label,box);
			add(box, (getWidth()-BOX_WIDTH)/2, (getHeight()-BOX_HEIGHT)/2);
			
		}else if(cmd.equals("Remove")){
			if(boxes.containsKey(name.getText())){
				remove(boxes.get(name.getText()));
				boxes.remove(name.getText());
			}
		}
		
	}
	public void mousePressed(MouseEvent e){
		lastLocation = new GPoint(e.getPoint());
		gObj = getElementAt(lastLocation);
	}

	public void mouseDragged(MouseEvent e){
		if(gObj != null){
			gObj.setLocation(lastLocation);
			lastLocation = new GPoint(e.getPoint());
		}
	}

	private GCompound createLabledBox(String label) {
		GCompound box = new GCompound();
		box.add(new GRect(BOX_WIDTH, BOX_HEIGHT));
		GLabel gLabel = new GLabel(label);
		box.add(gLabel ,(BOX_WIDTH - gLabel.getWidth()) / 2, (BOX_HEIGHT + gLabel.getAscent()) / 2 );
		return box;
	}
	
	private GPoint lastLocation;
	private GObject gObj;
	private Map<String, GCompound> boxes = new HashMap<String, GCompound>();
	private JTextField name;
}

