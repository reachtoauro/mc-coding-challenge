package com.cityconnect.api.service;

/**
 * interface for ConnectedCity Service
 * @author aurobindamondal
 *
 */
public interface ConnectedCityService {

	/**
	 * This method will evaluate if origin and destination cities are connected
	 * @param origin
	 * @param destination
	 * @return boolean
	 * @throws Exception
	 */
	boolean isConnected(String origin, String destination);

}
