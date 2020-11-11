package com.cityconnect.api.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import com.cityconnect.api.service.ConnectedCityService;

/**
 * @author aurobindamondal
 * this will test ConnectedCityResource class
 */
@SpringBootTest(classes = ConnectedCityResource.class)
public class ConnectedCityResourceTest {

	private ConnectedCityResource subject;
	private ConnectedCityService service;
	
	@Before
	public void setUp() throws Exception {
		service = Mockito.mock(ConnectedCityService.class);
		subject = new ConnectedCityResource(service);
	}
	
	/**
	 * test for empty origin city
	 */
	@Test
	public void testEmptyOrigin() {
		assertEquals("no", subject.checkIfConnected("", "destination"));
	}

	/**
	 * test empty destination city
	 */
	@Test
	public void testEmptyDestination() {
		assertEquals("no", subject.checkIfConnected("origin", ""));
	}
	
	/**
	 * test to check with connected cities
	 */
	@Test
	public void testCheckIfConnected() {
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		assertEquals("no", subject.checkIfConnected("Boston", "Albany"));
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		assertEquals("yes", subject.checkIfConnected("Boston", "Albany"));
	}
	
	/**
	 * test to check exception is handled properly
	 */
	@Test(expected = ResponseStatusException.class)
	public void testCheckIfConnectedException() {
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenThrow(ResponseStatusException.class);
		subject.checkIfConnected("Boston", "Albany");
		fail("Exception was expected");
	}
	

}
