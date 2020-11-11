package com.cityconnect.api.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author aurobindamondal
 * this will test CityGuide class
 */
@SpringBootTest(classes = CityGuide.class)
public class CityGuideTest {

	private CityGuide cityGuide;
	
	/**
	 * this will test all the methods of the subject class
	 */
	@Test
	public void testCityGuide() {
		cityGuide = new CityGuide();
		cityGuide.addConnection("Boston", "New York");
		cityGuide.addConnection("Philadelphia", "Newark");
		cityGuide.addConnection("Newark", "Boston");
		cityGuide.addConnection("Trenton", "Albany");
		String expectedGraph = "New York[Boston]Newark[Philadelphia, Boston]Trenton[Albany]Philadelphia[Newark]Boston[New York, Newark]Albany[Trenton]";
		assertEquals(cityGuide.printCityGuide(), expectedGraph);
		assertNotNull(cityGuide.getAdjacentCities("Boston"));
		assertEquals("[New York, Newark]", cityGuide.getAdjacentCities("Boston").toString());
	}

}
