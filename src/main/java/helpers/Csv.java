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

	public static String escape(String text) {
		if (hasOffendingChar(text)) {
			return "\"" + text.replace("\"", "\"\"") + "\"";
		}

		return text;
	}
}
