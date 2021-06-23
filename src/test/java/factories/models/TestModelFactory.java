package factories.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contracts.factories.IModelFactory;
import models.BaseModel;

class TestModelFactory {
	private class Model extends BaseModel {}

	protected IModelFactory<Model> factory;

	@BeforeEach
	void setUp() throws Exception {
		factory = new ModelFactory<TestModelFactory.Model>() {
			@Override
			public Model create() {
				// TODO Auto-generated method stub
				return new Model();
			}
		};
	}

	@Test
	void testCreate() {
		Model model = factory.create(25);
		assertEquals(25, model.getId());
	}
}
