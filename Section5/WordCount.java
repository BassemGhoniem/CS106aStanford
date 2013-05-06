import java.io.*;

import acm.program.*;
import acm.util.*;
import java.util.*;

public class WordCount extends ConsoleProgram {
	
	public void run(){
		ArrayList<String> lines = new ArrayList<String>();
		int[] count = new int[3];
		
		try{
			BufferedReader rd = openFileReader();
			while(true){
				String line = rd.readLine();
				if(line == null)break;
				lines.add(line);
			}
			rd.close();
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
		
		count[linesCount]=lines.size();
		
		for(int i = 0;i <lines.size();i++){
			StringTokenizer tokens = new StringTokenizer(lines.get(i)," /'");
			
			while(tokens.hasMoreTokens()){
				
				count[wordsCount] += 1;
				
				String word = tokens.nextToken();
				for(int j = 0;j < word.length();j++){
					char ch = word.charAt(j);
					if(Character.isLetterOrDigit(ch))
						
						count[charsCount] += 1;
				}
			}
		}
		
		println("Lines = " + count[linesCount]);
		println("Words = " + count[wordsCount]);
		println("Chars = " + count[charsCount]);
		
	}

	private BufferedReader openFileReader() {
		BufferedReader rd = null;
		while(rd == null){
			try{
				String name = readLine("File: ");
				rd = new BufferedReader(new FileReader(name));
			}catch(IOException ex){
				println("Can't open that file.");
			}
		}
		return rd;
	}
	private static final int linesCount = 0;
	private static final int wordsCount = 1;
	private static final int charsCount = 2;
}
