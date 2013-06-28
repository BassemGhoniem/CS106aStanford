/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
	
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		surferEntries.clear();
		update();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		if(!surferEntries.contains(entry)){
			surferEntries.add(entry);
			update();
		}
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGrid();
		drawNameGraph();
		
	}
	/**
	 * drawNameGraph() draw the name graph, if the name has rank zero 
	 * draw the point in the bottom of the canvas 
	 */
	
	
	
	private void drawNameGraph() {
		double  scale =(getHeight() - 2 * GRAPH_MARGIN_SIZE) / 1000.0;		
		double x = getWidth() / 11.0;
		for(int i =0; i < surferEntries.size();i++ ){
			
			NameSurferEntry entry = surferEntries.get(i);
			String name = entry.getName();
			Color color = getColor(i);
			
			int p = 0;
			while(p < 10){
				double xStart = x * p;
				double yStart =GRAPH_MARGIN_SIZE + entry.getRank(p++) * scale;
				GLabel gLabel = new GLabel(name +" "+ entry.getRank(p - 1) );
				/*
				 * the rank equal zero so
				 * it draw the point at the bottom
				 * and set the label to the name and *
				 */
				if(yStart==GRAPH_MARGIN_SIZE){
					yStart = getHeight()- GRAPH_MARGIN_SIZE;
					gLabel.setLabel(name +" *");
				}
				
				gLabel.setColor(color);
				add(gLabel, xStart,yStart);
				
				double xEnd = x * p;
				double yEnd = GRAPH_MARGIN_SIZE + entry.getRank(p) * scale;
				
				if(yEnd==GRAPH_MARGIN_SIZE)yEnd = getHeight()- GRAPH_MARGIN_SIZE;
				GLine gLine = new GLine(xStart , yStart, xEnd, yEnd);
				
				gLine.setColor(color);
				add(gLine);
				
				
				if(p == 10){
					GLabel lastGLabel = new GLabel(name+ " " + entry.getRank(p) );
					lastGLabel.setColor(color);
					if(yEnd == getHeight()-GRAPH_MARGIN_SIZE)lastGLabel.setLabel(name + " *");
					add(lastGLabel, xEnd,yEnd);
				}
			}
		}
		
	}
	/**
	 * getColor() assign color to the name graph according its index
	 * in the list take the index as parameter and return the color
	 * @param entryNum
	 * @return
	 */

	private Color getColor(int entryNum) {
		
		int indexOfName = entryNum + 1;
		if(indexOfName %4==0)return Color.MAGENTA;
		else if(indexOfName%4==1)return Color.BLACK;
		else if(indexOfName%4==2)return Color.RED;
		else if(indexOfName%4==3)return Color.BLUE;
		else return null;
	}
	/**
	 * drawGrid() assembles the horizontal and vertical lines on the grid
	 * in addition to the labels of the dates
	 */
	private void drawGrid() {
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight()-GRAPH_MARGIN_SIZE, getWidth(), getHeight()-GRAPH_MARGIN_SIZE));
		
		double verticalLinesX = getWidth() / 11.0;
		for(int i = 0; i < NDECADES; i++){
			double x = i * verticalLinesX;
			add(new GLine(x, 0, x, getHeight()));
			
			int date = 1900 + i * 10;
			GLabel label = new GLabel(""+date);
			double labelY = getHeight()- ((GRAPH_MARGIN_SIZE - label.getAscent())/2);
			add(label, x, labelY);
		}
		
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	/* iVar */
	private ArrayList<NameSurferEntry> surferEntries = new ArrayList<NameSurferEntry> ();


}
