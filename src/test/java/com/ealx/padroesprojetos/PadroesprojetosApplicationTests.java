package com.ealx.padroesprojetos;

import com.ealx.padroesprojetos.controller.ClienteRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PadroesprojetosApplicationTests {

    @Autowired
    private ClienteRestController controller;
	@Test
	void contextLoads() {
        assertNotNull(controller);
	}

}
