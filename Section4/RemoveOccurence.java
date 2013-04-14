/*
 * RemoveOccurence.java
 * -----------------------------
 * this class solves the problem 2 in section 4 
 * and is used to remove character from a string 
 */



import acm.program.*;
public class RemoveOccurence extends ConsoleProgram {
	public void run(){
		String str = readLine("Enter string ");
		String letter = readLine("Enter Letter to remove ");		
		str = removeAllOcurrence(str,letter.charAt(0));
		println(str);
	}

	private String removeAllOcurrence(String str, char letter) {
	
		
		for(int i = 0; i < str.length();i++ ){
			if(str.charAt(i)==letter){
				str=str.substring(0,i)+ str.substring(i+1);
				i--;
			}
		}
		return str;
	}

	
	
}
