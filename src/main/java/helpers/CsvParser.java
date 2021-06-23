package helpers;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import contracts.models.IModelBuilder;
import models.BaseModel;

public class CsvParser<T extends BaseModel> {
	private IModelBuilder<T> builder;

	public CsvParser(IModelBuilder<T> modelBuilder) {
		builder = modelBuilder;
	}

	public T parse(String csv) {
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
			builder.set("id", Integer.parseInt(partitions.get(0)));

			for (int i = 0; i < fields.length; i++) {
				String field = fields[i];
				String readField = unescape(partitions.get(i + 1));

				switch (builder.getFieldType(field)) {
				case "int":
					builder.set(field, Integer.parseInt(readField));
					break;
				case "java.time.LocalDate":
					builder.set(field, LocalDate.parse(readField));
					break;
				case "java.lang.String":
				default:
					builder.set(field, readField);
					break;
				}
			}
		} catch (IndexOutOfBoundsException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {

		}

		return builder.build();
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
		boolean quoteOpen = false;
		boolean previousWasQuote = false;
		int fieldLength = 0;

		heads.add(0);

		for (int i = 0; i < csv.length(); i++) {
			char c = csv.charAt(i);

			if (fieldLength == 0) {
				if (c == '"') {
					quoteOpen = true;
				}

				// Boundary of empty field
				if (c == ',') {
					heads.add(i + 1);
					fieldLength = 0;
					continue;
				}
			} else {
				if (quoteOpen) {
					if (previousWasQuote) {
						// Field boundary
						if (c == ',') {
							quoteOpen = false;
							previousWasQuote = false;
							heads.add(i + 1);
							fieldLength = 0;
							continue;
						}

						if (c != '"') {
							throw new IllegalArgumentException("Quote mismatch");
						}

						previousWasQuote = false;
					} else {
						if (c == '"') {
							previousWasQuote = true;
						}
					}
				} else {
					if (c == '"') {
						throw new IllegalArgumentException("Quote mismatch");
					}

					// Field boundary
					if (c == ',') {
						heads.add(i + 1);
						fieldLength = 0;
						continue;
					}
				}
			}

			fieldLength++;
		}

		if (quoteOpen && !previousWasQuote) {
			throw new IllegalArgumentException("Quote mismatch");
		}

		return heads;
	}
}