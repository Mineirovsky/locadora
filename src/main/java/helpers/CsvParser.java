package helpers;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import contracts.models.IModelBuilder;
import models.BaseModel;

public class CsvParser<T extends BaseModel> {
	private static class State {
		boolean quoteOpen = false;
		boolean previousWasQuote = false;
		int fieldLength = 0;

		boolean isIllegalOrMismatchedQuote(char c) {
			return (
				fieldLength > 0 &&
				(
					(quoteOpen && previousWasQuote && !(c == ',' || c == '"')) ||
					(!quoteOpen && c == '"')
				)
			);
		}

		boolean isFieldBoundary(char c) {
			return (
				c == ',' && (
					(fieldLength == 0) ||
					(fieldLength > 0 && (previousWasQuote || !quoteOpen))
				)
			);
		}

		boolean isQuoteToken(char c) {
			return fieldLength > 0 && quoteOpen && c == '"';
		}

		boolean isOpeningQuote(char c) {
			return fieldLength == 0 && c == '"';
		}

		boolean quotesAreMatching() {
			return !quoteOpen || previousWasQuote;
		}

		State next(char c) {
			if (isIllegalOrMismatchedQuote(c)) {
				return null;
			}

			if (isFieldBoundary(c)) {
				if (previousWasQuote) {
					quoteOpen = false;
					previousWasQuote = false;
				}
				fieldLength = 0;
				return this;
			}

			if (isQuoteToken(c)) {
				previousWasQuote = !previousWasQuote;
			}

			if (isOpeningQuote(c)) {
				quoteOpen = true;
			}

			fieldLength++;
			return this;
		}
	}

	private IModelBuilder<T> builder;

	public CsvParser(IModelBuilder<T> modelBuilder) {
		builder = modelBuilder;
	}

	public T parseLine(String csv) {
		String[] fields = builder.getFields();
		List<String> partitions = getLinePartitions(csv);

		if (partitions.size() != fields.length + 1) {
			throw new IllegalArgumentException(
				"Expected " +
				(fields.length + 1) +
				" fields, but " +
				partitions.size() +
				" were found"
			);
		}

		try {
			builder.set("id", partitions.get(0));

			for (int i = 0; i < fields.length; i++) {
				String field = fields[i];
				String readField = unescape(partitions.get(i + 1));

				builder.set(field, parseField(field, readField));
			}
		} catch (IndexOutOfBoundsException | NoSuchFieldException | IllegalAccessException e) {
			// Something is very wrong
			System.exit(-1);
		}

		return builder.build();
	}

	/**
	 * Parse the value into field's type
	 * @param field Field name
	 * @param value
	 * @return
	 */
	private Object parseField(String field, String value) {
		switch (builder.getFieldType(field)) {
		case "int":
			return Integer.parseInt(value);
		case "java.time.LocalDate":
			return LocalDate.parse(value);
		case "java.lang.String":
		default:
			break;
		}

		return value;
	}

	public static List<String> getLinePartitions(String csv) {
		List<String> partitions = new LinkedList<String>();
		List<Integer> heads = getFieldsHead(csv);

		for (int i = 0; i < heads.size(); i++) {
			if (i == heads.size() - 1) {
				partitions.add(csv.substring(heads.get(i)));
			} else {
				partitions.add(csv.substring(heads.get(i), heads.get(i + 1) - 1));
			}
		}

		return partitions;
	}

	public static String unescape(String csvField) {
		if (csvField.length() == 0 || csvField.charAt(0) != '"') {
			return csvField;
		}

		return csvField
			.substring(1, csvField.length() - 1)
			.replace("\"\"", "\"");
	}

	private static List<Integer> getFieldsHead(String csv) {
		List<Integer> heads = new LinkedList<Integer>();
		State state = new State();

		heads.add(0);

		for (int i = 0; i < csv.length(); i++) {
			char c = csv.charAt(i);

			if (state.isIllegalOrMismatchedQuote(c)) {
				throw new IllegalArgumentException("Quote mismatch");
			}

			if (state.isFieldBoundary(c)) {
				heads.add(i + 1);
			}

			state.next(c);
		}

		if (!state.quotesAreMatching()) {
			throw new IllegalArgumentException("Quote mismatch");
		}

		return heads;
	}
}
