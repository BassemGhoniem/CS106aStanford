import java.util.*;
import java.io.*;
import acm.util.*;
public class CitiesDB {
	
	public CitiesDB(String file){
		try{
			BufferedReader rd= new BufferedReader(new FileReader(file));
			while(true){
				String line = rd.readLine();
				if(line==null)break;
				parseLine(line);
			}rd.close();
		}catch(IOException r){
			throw new ErrorException(r);
		}
	}
	
	
	
	
	private void parseLine(String line){
		String name = line.substring(0, line.indexOf("-"));
		if(citiesDB.containsKey(name)){
			City curCity = citiesDB.get(name);
			String dest = line.substring(line.indexOf(">") + 1);
			curCity.addDest(dest);
		}else{
			City newCity = new City(name);
			citiesDB.put(name,newCity);
			String dest = line.substring(line.indexOf(">") + 1);
			newCity.addDest(dest);
			
		}

	}
	
	
	public City findCity(String name){
		if(citiesDB.containsKey(name))return citiesDB.get(name);
		else return null;
	}
	
	
	public Iterator<String> getIterator(){
		return citiesDB.keySet().iterator();
	}
	
	private Map<String,City> citiesDB = new HashMap<String,City>();
}
