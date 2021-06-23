package factories.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestCustomerFactory {
	CustomerFactory factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new CustomerFactory();
	}

	@Test
	void testCreate() {
		assertNotNull(factory.create());
	}
}
