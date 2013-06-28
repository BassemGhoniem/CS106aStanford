import java.util.*;
public class City {
	
	public City(String name){
		cityName = name;
	}
	public boolean isDestCity(String name){
		return  destCities.contains(name);
	}
	public String getCityName(){
		return cityName;
	}
	public void addDest(String city){
		destCities.add(city);
	}
	public Iterator<String> getDests(){
		return destCities.iterator();
	}
	
	
	private String cityName;
	private List<String> destCities = new ArrayList<String>();
}
