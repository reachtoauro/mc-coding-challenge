package com.cityconnect.api;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CityConnectApplication.class)
public class CityConnectApplicationTest {

	@Test
	public void contextLoads() {
		try {
			
		}catch(Exception ex) {
			fail("no exception was expected");
		}
	}
	

}
