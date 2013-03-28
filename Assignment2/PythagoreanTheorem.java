/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * this program computes the Pythagorean theorem
 * input two real numbers and output a real number
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		println("Enter values to compute the Pythagorean theorem.");
		double a = readDouble("a: ");
		double b = readDouble("b: ");
		double c = Math.sqrt(a*a+b*b);
		println("c: " + c);
	}
}
