package com.cityconnect.api.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CityGuideTest {

	private CityGuide cityGuide;
	
	@Test
	public void test() {
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
