/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	
	public void init() {
	    name = new JTextField(MAX_NAME_LENGTH);
	    name.addActionListener(this);
	    name.setActionCommand("Graph");
	    add(new JLabel("Name"), SOUTH);
	    add(name, SOUTH);
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    graph = new NameSurferGraph();
	    add(graph);
	    addActionListeners();
	    
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("Graph")){
			NameSurferEntry entry = dataBase.findEntry(name.getText());
			graph.addEntry(entry);
	
		}else if(cmd.equals("Clear")){
			graph.clear();
		}
	}
	/* INstance Variables*/
	private NameSurferGraph graph;
	private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	private JTextField name;
	
}