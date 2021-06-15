package helpers;

public class Csv {
	public static boolean hasOffendingChar(String text) {
		final String[] offendingChars = { ",", "\"", "\n" };
		
		for (String offendingChar: offendingChars) {
			if (text.contains(offendingChar)) {
				return true;
			}
		}
		
		return false;
	}
}
