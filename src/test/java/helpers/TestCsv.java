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
}
