package com.cityconnect.api.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import com.cityconnect.api.service.ConnectedCityService;

public class ConnectedCityResourceTest {

	private ConnectedCityResource subject;
	
	private ConnectedCityService service;
	
	@Before
	public void setUp() throws Exception {
		service = Mockito.mock(ConnectedCityService.class);
		subject = new ConnectedCityResource(service);
	}
	
	@Test
	public void testEmptyOrigin() {
		assertEquals("no", subject.checkIfConnected("", "destination"));
	}

	@Test
	public void testEmptyDestination() {
		assertEquals("no", subject.checkIfConnected("origin", ""));
	}
	
	@Test
	public void testCheckIfConnected() throws Exception {
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		assertEquals("no", subject.checkIfConnected("Boston", "Albany"));
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		assertEquals("yes", subject.checkIfConnected("Boston", "Albany"));
	}
	
	@Test(expected = ResponseStatusException.class)
	public void testCheckIfConnectedException() throws Exception {
		when(service.isConnected(Mockito.anyString(), Mockito.anyString())).thenThrow(ResponseStatusException.class);
		assertEquals("no", subject.checkIfConnected("Boston", "Albany"));
		fail("Exception was expected");
	}
	

}
