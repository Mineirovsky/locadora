package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Model1 extends BaseModel { }

class BaseModelTest {
	@Test
	void testEqualsBaseModelWithEqual() {
		BaseModel entity1 = new Model1();
		BaseModel entity2 = new Model1();
		
		entity1.setId(1);
		entity2.setId(1);

		assertTrue(entity1.equals(entity2));
	}
	
	@Test
	void testEqualsBaseModelWithDifferent() {
		BaseModel entity1 = new Model1();
		BaseModel entity2 = new Model1();
		
		entity1.setId(1);
		entity2.setId(2);

		assertFalse(entity1.equals(entity2));
	}
}
