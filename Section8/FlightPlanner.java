/*
 * File: FlightPlanner.java
 * ---------------------
 * Section 8 Solutions 
 */

import acm.program.*;
import java.util.*;
public class FlightPlanner extends ConsoleProgram {
	
	public void run() {
		setFont("Courier-24");
		println("Wellcom to Flight Planner!");
		println("Here's a list of all the cities in our database:");
		printDatabaseEntries();
		
		
		println("Let's plan a round-trip route!");
		String dest = readLine("Enter the Starting City: ");
		route.add(dest);
		
		
		
		while(true){
			println("From " + dest + "you can fly to:");
			printAvailableDests(dest);
			String chosenCity = readLine("Where do you want to go from " + route.get(route.size()-1) + " ? ");
			
			if(dataBase.findCity(chosenCity) != null){
				dest = chosenCity;
				route.add(dest);
				if(route.get(0).equals(dest))break;
			}else{
				println("You can't get to that city by direct fly.");
			}
			
		}
		println("The route you've chosen is:");
		printTheRoute();
	}
	
	
	
	
	private void printTheRoute(){
		for(int i = 0; i < route.size(); i++){
			print(route.get(i) + (i != route.size()-1 ?" -> ":""));
		}
	}
	
	
	
	
	private void printAvailableDests(String city) {
		Iterator<String> it = dataBase.findCity(city).getDests();
		while(it.hasNext()){
			String name = it.next();
			println(" " + name);
		}
	}
	
	
	
	private void printDatabaseEntries(){
		Iterator<String> it = dataBase.getIterator();
		while(it.hasNext()){
			String city = it.next();
			println(" " + city);
		}
	}
	
	
	
	private ArrayList<String> route = new ArrayList<String>();
	private CitiesDB dataBase = new CitiesDB("FlightPlanner.txt");	
}


