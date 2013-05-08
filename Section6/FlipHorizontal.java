

import acm.program.*;
import acm.graphics.*;
public class FlipHorizontal extends GraphicsProgram {
	public void run() {
		GImage image = new GImage("cry.jpg");
		GImage flipImage = flipHorizontal(image);
		
		//image.scale(0.1);
		add(image,0,0);
		
		//flipImage.scale(0.1);
		add(flipImage,flipImage.getWidth()+1,0);
	}

	private GImage flipHorizontal(GImage image) {
		int[][] imagePixels = image.getPixelArray();
		int height = imagePixels.length;
		int width  = imagePixels[0].length;
		
		for(int i = 0;i <height ; i++){			
			for(int j = 0;j < width/2; j++){
				int temp = imagePixels[i][j];
				imagePixels[i][j] = imagePixels[i][width - j - 1];
				 imagePixels[i][width - j - 1] = temp;
			}
		}
		return new GImage(imagePixels);
	}
}

