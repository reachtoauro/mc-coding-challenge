package com.cityconnect.api.atdd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class ATDD {

	@LocalServerPort
	int randomServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testBadRequest() throws URISyntaxException 
	{
		final String baseUrl = "http://localhost:"+randomServerPort+"/connected";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		assertEquals(400, result.getStatusCodeValue()); 
	}
	
	
	@Test
	public void testConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Newark");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertTrue(response.equals("yes")); 
	}
	@Test
	public void testUnConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Albany");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertTrue(response.equals("no")); 
	}

	@Test
	public void testInvalidCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "AA");
		queryParams.put("destination", "BB");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertTrue(response.equals("no")); 
	}
}
