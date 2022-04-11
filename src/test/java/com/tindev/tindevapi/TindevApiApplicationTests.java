package com.tindev.tindevapi;

import com.tindev.tindevapi.entities.UserEntity;
import com.tindev.tindevapi.repository.exceptions.RegraDeNegocioException;
import com.tindev.tindevapi.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TindevApiApplicationTests {

	@Test
	public void contextLoads() {
	}

}
