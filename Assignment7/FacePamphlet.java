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

public class FacePamphlet extends Program 
					implements FacePamphletConstants {
	
	
	private JTextField name;
	private JTextField status;
	private JTextField picture;
	private JTextField friend;
	private FacePamphletDatabase people = new FacePamphletDatabase(); ;
	private FacePamphletProfile currentProfile;
	private FacePamphletCanvas canvas;
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		canvas = new FacePamphletCanvas();
		add(canvas);
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
					canvas.displayProfile(currentProfile);
					canvas.showMessage(friendToAdd + " added as a friend");
				}else{
					canvas.showMessage(currentProfile.getName() + " already has " + friendToAdd + " as a friend");
				}
			}else{
				canvas.showMessage(friendToAdd + " does not exist");
			}
		}else{
			canvas.showMessage("Please select a profile to add friend");
		}
	}


	private void updateImage() {
		if(currentProfile != null){
			GImage image = null;
			try{
				image = new GImage(picture.getText());
				currentProfile.setImage(image);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Picture Updated");
			}catch(ErrorException ex){
				canvas.showMessage("Unable to open image file: " + picture.getText());
			}
		}else {
			canvas.showMessage("Please select a profile to change picture");
		}
	}


	private void changeStatus() {
		if(currentProfile != null){
			currentProfile.setStatus(status.getText());
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Status updated to " + status.getText());
		}else {
			canvas.showMessage("Please select a profile to change status");
		}
	}


	private void lookupProfile(String profileName) {
		if(!people.containsProfile(profileName)){
			currentProfile = null;
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + profileName + " does not exist");
		}else{
			currentProfile = people.getProfile(profileName);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("Displaying " + profileName);
		}
	}


	private void deleteProfile(String profileName) {
		currentProfile = null;
		canvas.displayProfile(currentProfile);
		if(!people.containsProfile(profileName)){
			canvas.showMessage("A profile with name " + profileName + " does not exist");
		}else{
			people.deleteProfile(profileName);
			canvas.showMessage("Profile of " + profileName + " deleted");
		}
	}


	private void addProfile(String profileName) {
		if(!people.containsProfile(profileName)){
			FacePamphletProfile profile = new FacePamphletProfile(profileName);
			currentProfile = profile;
			people.addProfile(profile);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("New profile created");
		}else{
			currentProfile = people.getProfile(profileName);
			canvas.displayProfile(currentProfile);
			canvas.showMessage("A profile with the name " + profileName + " already exists");
		}
	}

}
