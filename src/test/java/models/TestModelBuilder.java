package models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TestModelBuilder {
	private class Model extends BaseModel {
		public int number;
		public String name;
		public LocalDate date;
	}
	
	protected ModelBuilder<Model> builder;
	
	@BeforeEach
	void setUp() {
		Model model = new Model();
		builder = new ModelBuilder<Model>(model);
	}

	@Test
	void testBuild() {
		Model builtModel;

		try {
			builder.set("number", 42);
			builder.set("name", "Entity");
			builder.set("date", LocalDate.of(2021, 6, 1));
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			fail(e);
		}
		
		builtModel = builder.build();
		
		assertEquals(42, builtModel.number);
		assertEquals("Entity", builtModel.name);
		assertEquals(LocalDate.of(2021, 6, 1), builtModel.date);
	}

	@Test
	void testBuildWithInvalidFieldName() {
		assertThrows(
			NoSuchFieldException.class,
			() -> builder.set("num", 42)
		);
	}
	
	@Test
	void testBuildWithIllegalArgument() {
		assertThrows(
			IllegalArgumentException.class,
			() -> builder.set("number", "Illegal")
		);
	}
	
	@Test
	void testGetFieldType() {
		assertEquals("int", builder.getFieldType("number"));
		assertEquals("java.lang.String", builder.getFieldType("name"));
		assertEquals("java.time.LocalDate", builder.getFieldType("date"));
	}
	
	@Test
	void testGetFieldTypeWithInvalidField() {
		assertNull(builder.getFieldType("oops"));
	}
}
