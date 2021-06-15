package helpers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCsv {
	@Test
	void testHasOffendingChar() {
		assertTrue(Csv.hasOffendingChar("This is \"offensive\""));
		assertTrue(Csv.hasOffendingChar("Also, offensive"));
		assertTrue(Csv.hasOffendingChar("This is\noffensive too"));
	}

	@Test
	void testHasOffendingCharWithoutOffendingChar() {
		assertFalse(Csv.hasOffendingChar("This is not offensive"));
		assertFalse(Csv.hasOffendingChar("10"));
		assertFalse(Csv.hasOffendingChar(""));
	}
	
	@Test
	void testEscape() {
		assertEquals("\"\"\"offensive\"\"\"", Csv.escape("\"offensive\""));
		assertEquals("\"\"\"\"\"\"", Csv.escape("\"\""));
		assertEquals("\"Line\nbreak\"", Csv.escape("Line\nbreak"));
		assertEquals("\"Comma, separated\"", Csv.escape("Comma, separated"));
	}
	
	@Test
	void testEscapeWithNonOffendingChar() {
		assertEquals("compliant", Csv.escape("compliant"));
	}
}
