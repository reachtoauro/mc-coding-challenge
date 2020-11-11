package com.cityconnect.api.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import com.cityconnect.api.utils.CityTraversalUtility;

/**
 * @author aurobindamondal
 * to test ConnectedCityServiceImpl class
 */
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
	
	/**
	 * test service is working properly 
	 * with positive and negative test cases
	 */
	@Test
	public void testLoadAndRetrieveGraphData() {
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(false);
		assertFalse(subject.isConnected("origin", "destination"));
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenReturn(true);
		assertTrue(subject.isConnected("origin", "destination"));
	}

	/**
	 * test to check exceptions are handled properly
	 */
	@Test(expected = RuntimeException.class)
	public void testLoadAndRetrieveGraphDataException() {
		when(cityTraversalUtility.isConnected(Mockito.anyString(), Mockito.anyString())).thenThrow(RuntimeException.class);
		subject.isConnected("origin", "destination");
	}

}
