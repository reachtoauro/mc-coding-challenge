package com.cityconnect.api.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.cityconnect.api.model.CityGuide;

/**
 * 
 * Leverages a graph data structure allowing the caller to evaluate if two cities are connected
 * @author aurobindamondal
 *
 */
@Component
public class CityTraversalUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CityTraversalUtility.class);

	
	@Autowired
	private CityGuide cityGuide;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	/**
	 * Use constructor injection for loading resource data.
	 * 
	 * @param resourceLoader to retrieve external resources
	 * @param cityGuide data structure to maintain the city guide
	 */
	public CityTraversalUtility(ResourceLoader resourceLoader, CityGuide cityGuide) throws Exception{
		this.cityGuide = cityGuide;
		this.resourceLoader = resourceLoader;
		loadData("classpath:city.txt");

	}

	/**
	 * This method evaluates if two cities are connected based on given origin and destination
	 * @param origin
	 * @param destination
	 * @return boolean
	 */
	public boolean isConnected(String origin, String destination) {
		// maintain list of cities that we have visited
        Set<String> visited = new LinkedHashSet<>();
        
        // create a queue for city traversal
        Queue<String> queue = new LinkedList<>();
        
        //check if the origin and destination both cities are present in the graph
        if(!cityGuide.isValidCity(origin) && !cityGuide.isValidCity(destination)) {
        	return false;
        }
        // begin with your origin city
        queue.add(origin);
        visited.add(origin);
        
        // traverse cities to see if destination is connected
        while (!queue.isEmpty()) {
        	// pop the current city from queue
            String currentCity = queue.poll();
            // check if we reached the destination?
            if (currentCity.equals(destination)) {
				return true;
			}
            
            // now add current city's adjacent cities to the queue which are not visited
            for (String city : cityGuide.getAdjacentCities(currentCity)) {
                if (!visited.contains(city)) {
                    visited.add(city);
                    queue.add(city);
                }
            }
        }
        
        // destination never reached
        return false;
    }
	
	/**
	 * Retrieve resource data from the classpath and load the city guide datastructure
	 */
	private void loadData(String cityConnectionsFilePath) {
		Resource resource = resourceLoader.getResource(cityConnectionsFilePath);
		InputStream in;
		BufferedReader reader;
		try {
			in = resource.getInputStream();
			reader = new BufferedReader(new InputStreamReader(in));
			while (true) {
				String line = reader.readLine();
				if (line != null) {
					String[] city = line.split(",");
					cityGuide.addConnection(city[0].trim(), city[1].trim());
				} else {
					break;
				}
			}
			reader.close();
			LOGGER.debug("City guide loaded successfully! {}",cityGuide.printCityGuide());
		} catch (Exception ex) {
			LOGGER.error("Error occured while loading file or populating city guide", ex);
		}
	}

}
