package com.g10.demo;

import com.g10.demo.exception.AppException;
import com.g10.demo.plugins.PluginManager;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class DemoApplicationTests {
	PluginManager pluginManager = new PluginManager();
	@Test
	void testGetInvalidServicePlugin() {
		assertThrows(AppException.class, () -> {
			pluginManager.getServerPlugin("invalid-service");
		});
	}

}
