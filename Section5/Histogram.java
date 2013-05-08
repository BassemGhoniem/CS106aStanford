import acm.program.*;
import java.util.*;
import java.io.*;
import acm.util.*;
public class Histogram extends ConsoleProgram {
	private ArrayList<String> lines = new ArrayList<String>();
	private String[] histogram = new String[11];
	
	
	public void run(){		
		readFile();
		initHistogram();
		histogram();
		printHistogram();
		
		
	}

	private void printHistogram() {
		for(int i =0;i< histogram.length;i++){
			String label;
			switch(i){
				case 	0: label = "00 - 09";break;					
				case 	10: label = "    100" ;break;
				default :label = i * 10 + " - " + (i * 10 + i); 	break;
				
			}
			
			println(label + " : " + histogram[i]);
		}
	}

	
	private void histogram() {
		for(int i = 0 ;i <lines.size() ; i++){
			Integer grade = Integer.parseInt(lines.get(i));
			histogram[grade / 10] += "*";
		}
	}
	private void initHistogram() {
		for(int i = 0;i < histogram.length; i++){
			histogram[i]="";
		}
		
	}

	private void readFile() {
		try{
			BufferedReader rd = openFileReader();
			while(true){
				String line = rd.readLine();
				if(line == null)break;
				lines.add(line);
			}
		}catch(IOException ex){
			throw new ErrorException(ex) ;
		}
	}

	private BufferedReader openFileReader() {
		BufferedReader rd = null;
		while(rd == null){
			try{				
				String file = readLine("File: ");
				rd = new BufferedReader(new FileReader(file));
				}catch(IOException ex){
					println("Please Enter Correct Name :");
				}
		}
		return rd;
	}
}
