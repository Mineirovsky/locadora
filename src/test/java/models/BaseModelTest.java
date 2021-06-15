package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Model1 extends BaseModel {
	public String stringParam;
	public int intParam;
}

class BaseModelTest {
	@Test
	void testCompareToSmaller() {
		BaseModel entity1 = new Model1();
		BaseModel entity2 = new Model1();
		
		entity1.setId(1);
		entity2.setId(2);
		
		assertTrue(entity2.compareTo(entity1) > 0);
	}
	
	@Test
	void testCompareToEquals() {
		BaseModel entity1 = new Model1();
		BaseModel entity2 = new Model1();
		
		entity1.setId(1);
		entity2.setId(1);
		
		assertTrue(entity1.compareTo(entity2) == 0);
	}
	
	@Test
	void testCompareToGreater() {
		BaseModel entity1 = new Model1();
		BaseModel entity2 = new Model1();
		
		entity1.setId(1);
		entity2.setId(2);
		
		assertTrue(entity1.compareTo(entity2) < 0);
	}

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

	@Test
	void testToCsv() {
		Model1 entity = new Model1();
		entity1.setId(1);
		entity1.intParam = 0;
		entity1.stringParam = "text";
		
		String csv = entity.toCsv();
		assertEquals("1,0,text", csv);
	}

}
