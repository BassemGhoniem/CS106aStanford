/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	private GLabel message;
	private GLabel status;
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		 message = new GLabel("");
		 message.setFont(MESSAGE_FONT);
		 status = new GLabel("");
		 status.setFont(PROFILE_STATUS_FONT);
		
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		message.setLabel(msg);
		double msgX = (getWidth() - message.getWidth()) / 2;
		double msgY = getHeight() - BOTTOM_MESSAGE_MARGIN;
		add(message , msgX, msgY);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		
		if(profile != null){	
			/*
			 * Displaying the Name of the profile
			 */
			
			GLabel name = new GLabel(profile.getName());
			name.setFont(PROFILE_NAME_FONT);
			name.setColor(Color.blue);
			double nameY = TOP_MARGIN + name.getAscent();
			add(name, LEFT_MARGIN, nameY);
			
			/*
			 * show the Image (if any) of the profile
			 */
			
			
			double imageY = name.getY() + IMAGE_MARGIN;
			if(profile.getImage() == null){
				GRect rect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
				add(rect, LEFT_MARGIN,imageY);
				GLabel noImage = new GLabel("No Image");
				noImage.setFont(PROFILE_IMAGE_FONT);
				double noImageX = LEFT_MARGIN + (IMAGE_WIDTH -noImage.getWidth()) / 2;
				double noImageY = imageY + (IMAGE_HEIGHT + noImage.getAscent()) / 2;
				add(noImage, noImageX, noImageY);
			}else{
				GImage image = profile.getImage();
				image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
				add(image, LEFT_MARGIN,imageY);
			}
			
			/*
			 * Displaying the status (if any) of the profile
			 */
			
			if(profile.getStatus().equals("")){
				status.setLabel("No current status");
			}else{
				status.setLabel(profile.getName() + " is " + profile.getStatus());
			}
			double statusY = imageY + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent();
			add(status, LEFT_MARGIN, statusY);
			
			/*
			 * displaying the list of friends
			 */
			
			GLabel friends = new GLabel("Friends:");
			friends.setFont(PROFILE_FRIEND_LABEL_FONT);
			add(friends, getWidth() / 2, imageY);
			double friendY = imageY;
			for(String friendName : profile.getFriends()){
				
				GLabel friend = new GLabel(friendName);
				friendY += friend.getHeight();
				friend.setFont(PROFILE_FRIEND_FONT);
				add(friend, getWidth() / 2, friendY);
			}
		}

	}

	
}
