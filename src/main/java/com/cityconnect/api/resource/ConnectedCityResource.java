package com.cityconnect.api.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cityconnect.api.service.ConnectedCityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * REST API resource file with all the available APIs
 * 
 * @author aurobindamondal
 *
 */

@RestController
@RequestMapping("/connected")
@EnableSwagger2
@Api(tags = "City Connect APP", value = "CityConnectAPP")
public class ConnectedCityResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCityResource.class);

	private ConnectedCityService connectedCityService;

	/**
	 * controller dependency injection
	 * 
	 * @param cityService
	 */
	public ConnectedCityResource(ConnectedCityService connectedCityService) {
		this.connectedCityService = connectedCityService;
	}

	/**
	 * RESTful API to show if they the origin city and the destination city are
	 * connected. 
	 * If either origin or destination is null or empty, simply return no.
	 * 
	 * @param origin      city
	 * @param destination city
	 * @return yes or no based on the whether cities are connected
	 */
	@ApiOperation(value = "To determine if two cities are connected", notes = "Returns yes if they are connected else no ", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success. the cities are connected", response = String.class),
			@ApiResponse(code = 500, message = "Internal server Error occured while finding the cities are connected", response = Exception.class) })
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String checkIfConnected(
			@ApiParam(value = "Origin City") @RequestParam(name = "origin", required = true) String origin,
			@ApiParam(value = "Destination City") @RequestParam(name = "destination", required = true) String destination)
			 {
		try {
			if (origin.isEmpty() || destination.isEmpty()) {
				return "no";
			}
			
			LOGGER.info("Request received: origin {} and destination {}", origin, destination);

			if (connectedCityService.isConnected(origin, destination)) {
				return "yes";
			} else {
				return "no";
			}
		} catch (Exception ex) {
			LOGGER.error("Exception occured", ex);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
