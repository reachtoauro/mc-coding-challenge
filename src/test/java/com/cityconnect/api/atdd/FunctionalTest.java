package com.cityconnect.api.atdd;

import static org.junit.Assert.assertEquals;

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

/**
 * This is a quick funcationlity test just couple of ATDD
 * cases which we can move to feature file later
 * @author aurobindamondal
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class FunctionalTest {

	@LocalServerPort
	int randomServerPort;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	/**
	 * this is to test 400 BAD Request
	 * @throws URISyntaxException
	 */
	@Test
	public void testBadRequest() throws URISyntaxException 
	{
		final String baseUrl = "http://localhost:"+randomServerPort+"/connected";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
		assertEquals(400, result.getStatusCodeValue()); 
	}
	
	
	/**
	 * this is to test a positive case where we will test with connected cities
	 * @throws URISyntaxException
	 */
	@Test
	public void testConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Newark");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertEquals("yes", response); 
	}
	/**
	 * this is a negative test case where we will test with to disconnected
	 * @throws URISyntaxException
	 */
	@Test
	public void testUnConnectedCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "Boston");
		queryParams.put("destination", "Albany");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertEquals("no", response);
	}

	/**
	 * this is a negative test case where we will test with unknown cities
	 * @throws URISyntaxException
	 */
	@Test
	public void testInvalidCities() throws URISyntaxException 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("origin", "AA");
		queryParams.put("destination", "BB");
        String response = restTemplate.getForObject("/connected?origin={origin}&destination={destination}", String.class, queryParams);
        assertEquals("no", response); 
	}
}
