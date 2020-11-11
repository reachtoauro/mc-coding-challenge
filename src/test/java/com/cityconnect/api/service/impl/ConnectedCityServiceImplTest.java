package com.cityconnect.api.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.cityconnect.api.utils.CityTraversalUtility;

@SpringBootTest(classes = ConnectedCityServiceImpl.class)
public class ConnectedCityServiceImplTest {

	@InjectMocks
	private ConnectedCityServiceImpl subject;
	@Mock
	private CityTraversalUtility cityTraversalUtility;
	
	@Before
	public void setUp() throws Exception {
		subject = new ConnectedCityServiceImpl();
		cityTraversalUtility = Mockito.mock(CityTraversalUtility.class);
		ReflectionTestUtils.setField(subject, "cityTraversalUtility", cityTraversalUtility);
	}
	
	@Test
	public void testLoadAndRetrieveGraphData() throws Exception {
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		assertNotNull(subject.isConnected("origin", "destination"));
		assertFalse(subject.isConnected("origin", "destination"));
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		assertNotNull(subject.isConnected("origin", "destination"));
		assertTrue(subject.isConnected("origin", "destination"));
	}

	@Test(expected = RuntimeException.class)
	public void testLoadAndRetrieveGraphDataException() throws Exception {
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenThrow(RuntimeException.class);
		assertNotNull(subject.isConnected("origin", "destination"));
	}

}
