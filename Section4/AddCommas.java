/*
 * File: AddCommas.java
 * ---------------------
 * This class solves problem no 1 in the section 4
 * used for adding commas to a numeric string 
 */

import acm.program.*;

public class AddCommas extends ConsoleProgram {
	public void run() {
		while(true){
			String digits = readLine("Entera numeric string: ");
			if(digits.length() == 0)break;
			println(addCommasToNumericString(digits));
		}
	}

	private String addCommasToNumericString(String digits) {
		String result = "";
		
		for(int i = 1;i <= digits.length();i++){
			
			int index = digits.length() - i ;
			result = digits.charAt(index)+result;
			
			if(i % 3 == 0 && index != 0)result = ","+result  ;
			
			
		}
		return result;
	}
}

