package com.tindev.tindevapi;

import com.tindev.tindevapi.dto.user.UserDTO;
import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.enums.ProgLangs;
import com.tindev.tindevapi.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TindevApiApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void deverRetornarUsername() throws RegraDeNegocioException {
		Optional<UserEntity> username = userService.findByUsername("string");

		System.out.println(username.stream().findFirst().get().getUsername());
		assertEquals("string", username.stream().findFirst().get().getUsername());
	}

	@Test
	public void deveGerarUmaExceptionAoBuscarUsername()  {
		assertThrows(RegraDeNegocioException.class, () -> {
			userService.findByUsername("adasdsagsf");

		});
	}

	@Test
	public void contextLoads() {
	}

}
