package com.cityconnect.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityconnect.api.service.ConnectedCityService;
import com.cityconnect.api.utils.CityTraversalUtility;

/**
 * service to provide information about the connectivity between two cities
 * 
 * @author aurobindamondal
 *
 */
@Service
public class ConnectedCityServiceImpl implements ConnectedCityService {

	@Autowired
	private CityTraversalUtility cityTraversalUtility;

	/**
	 * this will evaluate whether two cities are connected
	 *@param origin			city
	 *@param destination	city
	 *@return boolean
	 */
	@Override
	public boolean isConnected(String origin, String destination) {
		return cityTraversalUtility.isConnected(origin, destination);
	}
}
