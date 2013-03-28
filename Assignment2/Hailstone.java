/*
 * File: Hailstone.java
 * Name: Bassem Ghoniem
 * --------------------
 * Hailstone program
 * Pick some positive integer and call it n.
 * If n is even, divide it by two.
 * If n is odd, multiply it by three and add one.
 * Continue this process until n is equal to one.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int num ;
		int counter = 0;// for counting the steps of the process to arrive 1
		/*as long as the user entered a negative number or zero ask the user for positive one.*/
		while(true){
			num = readInt("Entre a number: ");
			if(num<=0)println("Please Enter Positive Number.");
			else break;
		}
		/*here's the game.*/
		while(num > 1){
			if(isEven(num)){
				num /= 2;
				println(" is even, so I take the half: " + num);
			}else{
				num = 3 * num + 1;
				println(" is odd, so I take 3n + 1: " + num);
			}
			counter++;
		}
		println("The process took "+ counter + " to reach 1");
	
	}
	
	/*
	 * Predicate method to determine if the number is positive or not
	 */
	private boolean isEven(int num){
		if(num%2==0){
			print(num);// to print the number on the console window before doing any operation on it
			return true;
		}else{
			print(num);// to print the number on the console window before doing any operation on it
			return false;
		}
		
	}
}

