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
	private FacePamphletProfile currentProfile;
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		currentProfile = null;
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
			addProfile(profileName);	
		}else if(e.getActionCommand().equals("Delete")){
			deleteProfile(profileName);			
		}else if(e.getActionCommand().equals("Lookup")){
			lookupProfile(profileName);			
		}else if(e.getActionCommand().equals("Change Status")){
			changeStatus();		
		}else if(e.getActionCommand().equals("Change Picture")){
			updateImage();			
		}else if(e.getActionCommand().equals("Add Friend")){
			addFriend();
		}
	}


	private void addFriend() {
		if(currentProfile != null){
			String friendToAdd = friend.getText();
			if(people.containsProfile(friendToAdd)){
				if(currentProfile.addFriend(friendToAdd)){
					people.getProfile(friendToAdd).addFriend(currentProfile.getName());
					println(friendToAdd +" added as a friend");
					println("--> Current profile: " + currentProfile.toString());
				}else{
					println("Friend already exists");
				}
			}else{
				println("This person does not exist in DB");
			}
		}else{
			println("Please select a profile to update friends list.");
			println("--> No Current profile");
		}
	}


	private void updateImage() {
		if(currentProfile != null){
			GImage image = null;
			try{
				image = new GImage(picture.getText());
				currentProfile.setImage(image);
				println("Picture Updated");
				println("--> Current profile: " + currentProfile.toString());
			}catch(ErrorException ex){
				println("This Image doesn't exist in the DB");
			}
		}else {
			println("Please select a profile to update Image.");
			println("--> No Current profile");
		}
	}


	private void changeStatus() {
		if(currentProfile != null){
			currentProfile.setStatus(status.getText());
			println("Status updated to " + status.getText());
			println("--> Current profile: " + currentProfile.toString());
		}else {
			println("Please select a profile to change status");
			println("--> No Current profile");
		}
	}


	private void lookupProfile(String profileName) {
		if(!people.containsProfile(profileName)){
			println("Lookup: profile with name " + profileName + " does not exist");
			currentProfile = null;
		}else{
			currentProfile = people.getProfile(profileName);
			println("Lookup: " + people.getProfile(profileName).toString() );
			println("--> Current profile: " + currentProfile.toString());
		}
	}


	private void deleteProfile(String profileName) {
		if(!people.containsProfile(profileName)){
			println("Delete: profile with name " + profileName + " does not exist");
		}else{
			people.deleteProfile(profileName);
			println("Delete: profile of " + profileName +" deleted" );
		}
		currentProfile = null;
		println("--> No Current profile");
	}


	private void addProfile(String profileName) {
		if(!people.containsProfile(profileName)){
			FacePamphletProfile profile = new FacePamphletProfile(profileName);
			currentProfile = profile;
			people.addProfile(profile);
			println("Add: new profile: " + profile.toString());
			println("--> Current profile: " + currentProfile.toString());
		}else{
			currentProfile = people.getProfile(profileName);
			println("Add: profile for " + profileName +" Already exists "
					+ people.getProfile(profileName).toString());	
		}
	}

}
