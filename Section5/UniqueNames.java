import acm.program.*;
import java.util.*;
public class UniqueNames extends ConsoleProgram{
	public void run(){
		ArrayList<String> names = new ArrayList<String>();
		while(true){
			String name = readLine("Enter Name: ");
			if(name.equals(""))break;
			if(!names.contains(name))names.add(name);
		}
		println("Unique name list contains :");
		for(int i = 0; i < names.size(); i++){
			println(names.get(i));
		}
	}
}
