package org.isnov.training;

import lombok.extern.slf4j.Slf4j;
import org.isnov.training.app.entities.UserGroup;
import org.isnov.training.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class TrainingApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {

	}

	@Test
	void createUserGroup(){
		UserGroup userGroup = userService.createGroup("Champion d'afrique", "");
		log.warn(userGroup.getName(), userGroup.getUserGroupId(), userGroup.getDescription());

		assertEquals(userGroup.getName(), "Champion d'afrique");
	}

}
