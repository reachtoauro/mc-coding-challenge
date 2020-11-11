package com.cityconnect.api.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * @author aurobindamondal
 * Graph is used to represent the City Guide with all cities and
 * their adjacent cities with which they are connected
 * which will help determine whether the cities in question are 
 * connected or not
 *
 */
@Component
public class CityGuide {

	/**
	 * Map holds the origin city and all of it's adjacent cities
	 */
	private Map<String, List<String>> adjacentCities = new HashMap<>();
	
	
	/**
	 * getting the adjacent cities
	 * @param name
	 * @return List<City>
	 */
	public List<String> getAdjacentCities(String name) {
        return adjacentCities.get(name);
    }
	
	/**
	 * to quickly check if the city exists in the graph
	 * @param city
	 * @return boolean
	 */
	public boolean isValidCity(String city) {
        return adjacentCities.containsKey(city);
    }
	
	/**
	 * to add bidirectional connection between the connected cities
	 * @param origin city
	 * @param destination city
	 */
	public void addConnection(String origin, String destination) {
        
        if (!adjacentCities.containsKey(origin))
        	addCity(origin);

		if (!adjacentCities.containsKey(destination))
			addCity(destination);
		
        adjacentCities.get(origin).add(destination);
        adjacentCities.get(destination).add(origin);
    }
	
	/**
	 * adding city to the map
	 * @param city name
	 */
	public void addCity(String name) {
		adjacentCities.put(name, new ArrayList<>());
    }

	/**
	 * print the Guide
	 */
	public String printCityGuide() {
        StringBuilder sb = new StringBuilder();
        for(String city : adjacentCities.keySet()) {
            sb.append(city);
            sb.append(adjacentCities.get(city));
        }
        return sb.toString();
    }
	
}
