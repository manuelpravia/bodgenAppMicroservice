package com.mpraviap.user_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
		webEnvironment = RANDOM_PORT,
		properties = {"spring.cloud.config.enabled=false"}
)
@ContextConfiguration
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
