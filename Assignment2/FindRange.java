/*
 * File: FindRange.java
 * Name: Bassem Ghoniem
 * --------------------
 * Find Range program finds the range of a set of integers
 * and uses the zero as sentinel value 
 */

import acm.program.*;

public class FindRange extends ConsoleProgram 
{
	public void run() 
	{
		println("This program finds the largest and smallest numbers.");
		println("Enter Zero to finish the inputs.");
		int num = readInt("? ");
		int smallestNum=num;		
		int largestNum=num;
		
		while(num != 0)
		{			
			if(smallestNum>num)smallestNum = num;
			else if(largestNum<num)largestNum=num;
			num = readInt("? ");
		}
		if(smallestNum==0 && largestNum==0)
		{
			println("You don't enter anyvalue else 0 ");
		}
		else
		{
			println("smallest: "+ smallestNum);
			println("largest: "+largestNum);
		}
		
			
	}
}

