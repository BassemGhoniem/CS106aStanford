/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends ConsoleProgram 
					implements FacePamphletConstants {
	
	
	private JTextField name;
	private JTextField status;
	private JTextField picture;
	private JTextField friend;
	private FacePamphletDatabase people = new FacePamphletDatabase(); ;
	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		initInteractors();
		addActionListeners();
    }


	private void initInteractors() {
		name = new JTextField(TEXT_FIELD_SIZE);
		
		add(new JLabel("Name"), NORTH);
		name.addActionListener(this);
		name.setActionCommand("Add");
		add(name, NORTH);
		
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		status = new JTextField(TEXT_FIELD_SIZE);
		status.setActionCommand("Change Status");
		status.addActionListener(this);
		
		picture = new JTextField(TEXT_FIELD_SIZE);
		picture.setActionCommand("Change Picture");
		picture.addActionListener(this);
		
		friend = new JTextField(TEXT_FIELD_SIZE);
		friend.setActionCommand("Add Friend");
		friend.addActionListener(this);
		
		add(status, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		add(picture, WEST);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		add(friend, WEST);
		add(new JButton("Add Friend"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
	}
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
		String profileName = name.getText();
		if(e.getActionCommand().equals("Add")){
			
			
			if(!people.containsProfile(profileName)){
				FacePamphletProfile profile = new FacePamphletProfile(profileName);
				people.addProfile(profile);
				println("Add: new profile: " + profile.toString());
			}else{
				println("Add: profile for " + profileName +" Already exists "
						+ people.getProfile(profileName).toString());	
			}
			
			
		}else if(e.getActionCommand().equals("Delete")){
			
			if(!people.containsProfile(profileName)){
				println("Delete: profile with name " + profileName + " does not exist");
			}else{
				people.deleteProfile(profileName);
				println("Delete: profile of " + profileName +" deleted" );
			}
			
		}else if(e.getActionCommand().equals("Lookup")){
			
			if(!people.containsProfile(profileName)){
				println("Lookup: profile with name " + profileName + " does not exist");
			}else{
				println("Lookup: " + people.getProfile(profileName).toString() );
			}
			
			
		}if(e.getActionCommand().equals("Change Status")){
			println("Change Status: " );
		}if(e.getActionCommand().equals("Change Picture")){
			println("Change Picture: " +picture.getText());
		}if(e.getActionCommand().equals("Add Friend")){
			println("Add Friend: " + friend.getText());
		}
	}

}
