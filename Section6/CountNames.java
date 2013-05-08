import acm.program.*;
import java.util.*;
public class CountNames extends ConsoleProgram{
	public void run(){
		Map<String,Integer> namesCount = new HashMap<String, Integer>();
		while(true){
			String name = readLine("Enter Name: ");
			if(name.equals(""))break;
			if(namesCount.containsKey(name)){
				int value = namesCount.get(name) + 1;
				namesCount.put(name, value);
			}else{
				namesCount.put(name, 1);
			}
		}
		Iterator<String> iterator = namesCount.keySet().iterator();
		while(iterator.hasNext()){
			String name = iterator.next();
			println("Entry [" + name + "] has Count "+ namesCount.get(name).toString() );
		}
	}

}
