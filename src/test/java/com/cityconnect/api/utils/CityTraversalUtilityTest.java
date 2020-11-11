package com.cityconnect.api.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.util.ReflectionTestUtils;

import com.cityconnect.api.model.CityGuide;

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
	
	@Test
	public void testConnectivity() {
		String originCity = "Boston";
		String destinationCity = "Newark";
		assertTrue(subject.isConnected(originCity, destinationCity));
	}
	
	@Test
	public void testNotConnected() {
		String originCity = "Philadelphia";
		String destinationCity = "Albany";
		assertFalse(subject.isConnected(originCity, destinationCity));
	}
	
	@Test(expected = NullPointerException.class)
	public void testConnectivityNull() throws Exception {
		new CityTraversalUtility(null, null);
	}

}
