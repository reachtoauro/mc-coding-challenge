package com.cityconnect.api.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;

import com.cityconnect.api.model.CityGuide;

/**
 * @author aurobindamondal
 * to test CityTraversalUtility class
 */
@SpringBootTest(classes = CityTraversalUtility.class)
public class CityTraversalUtilityTest {

	private CityGuide cityGuide;
	@InjectMocks
	private CityTraversalUtility subject;
	
	@Before
	public void setup() throws Exception {
		cityGuide = new CityGuide();
		cityGuide.addConnection("Boston", "New York");
		cityGuide.addConnection("Philadelphia", "Newark");
		cityGuide.addConnection("Newark", "Boston");
		cityGuide.addConnection("Trenton", "Albany");
		subject = new CityTraversalUtility(new DefaultResourceLoader(), cityGuide);
	}
	
	/**
	 * to test with connected cities
	 */
	@Test
	public void testConnectivity() {
		String originCity = "Boston";
		String destinationCity = "Newark";
		assertTrue(subject.isConnected(originCity, destinationCity));
	}
	
	/**
	 * to test with not connected cities
	 */
	@Test
	public void testNotConnected() {
		String originCity = "Philadelphia";
		String destinationCity = "Albany";
		assertFalse(subject.isConnected(originCity, destinationCity));
	}
	
	/**
	 * to test if exception is handled properly
	 */
	@Test(expected = NullPointerException.class)
	public void testConnectivityNull() {
		new CityTraversalUtility(null, null);
	}

}
