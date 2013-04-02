/* MyProgram.java
 * --------------
 * Modify this file any way you like (or create additional files in
 * the ACMStarterProject) in order to experiment with the capabilities
 * of the ACM libraries.
 * 
 * NOTE: While you are free to do whatever you like with this project,
 * it is still best to use the assignment-specific starter files
 * for actual assignments.
 */
import acm.graphics.*;
import acm.program.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {
    private static final int NUM_CIRCLES = 20;
    private static final double SMALLEST_RADIUS = 5;
    private static final double LARGEST_RADIUS = 50;
    public void run() {
        for(int i = 0; i < NUM_CIRCLES; i++){
        	double radius =rgen.nextDouble(SMALLEST_RADIUS, LARGEST_RADIUS);
        	double x = rgen.nextDouble(0, getWidth()- 2*radius);
        	double y = rgen.nextDouble(0, getHeight() -2* radius);
        	GOval circle = new GOval(2*radius,2*radius);
        	circle.setFilled(true);
        	circle.setColor(rgen.nextColor());
        	add(circle,x,y);
        }
    }
    private RandomGenerator rgen = RandomGenerator.getInstance();

    
}
