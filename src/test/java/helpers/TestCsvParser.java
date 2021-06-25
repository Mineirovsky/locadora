package helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import contracts.models.IModelBuilder;
import models.BaseModel;

class TestCsvParser {
	IModelBuilder<BaseModel> builderMock;
	Map<String, Object> setCalls;

	@BeforeEach
	void setUp() {
		setCalls = new HashMap<String, Object>();
		builderMock = new IModelBuilder<BaseModel>() {
			@Override
			public IModelBuilder<BaseModel> set(String field, Object value)
					throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
				setCalls.put(field, value);
				return builderMock;
			}

			@Override
			public BaseModel build() {
				// TODO Auto-generated method stub
				return new BaseModel() {};
			}

			@Override
			public String[] getFields() {
				String[] fields = {"date", "name"};
				return fields;
			}

			@Override
			public String getFieldType(String field) {
				switch (field) {
				case "name":
					return "java.lang.String";
				case "date":
					return "java.time.LocalDate";
				default:
					return null;
				}
			}

		};
	}

	@Test
	void testParse() {
		CsvParser<BaseModel> parser = new CsvParser<BaseModel>(builderMock);

		assertNotNull(
			parser.parse("42,1999-12-31,\"John, the terrible\"")
		);

		assertEquals("42", setCalls.get("id"));

		assertEquals(LocalDate.of(1999, 12, 31), setCalls.get("date"));

		assertEquals("John, the terrible", setCalls.get("name"));
	}

	@Test
	void testGetLinePartitions() {
		List<String> partitions = CsvParser.getLinePartitions("123,\"\",\",\"\",,,\"\"\"");
		assertEquals(3, partitions.size());
		assertEquals("123", partitions.get(0));
		assertEquals("\"\"", partitions.get(1));
		assertEquals("\",\"\",,,\"\"\"", partitions.get(2));
	}

	@Test
	void testGetLinePartitionsWithOnlyCommas() {
		List<String> partitions = CsvParser.getLinePartitions(",,,,");
		assertEquals(5, partitions.size());
		assertEquals("", partitions.get(0));
	}

	@Test
	void testGetLinePartitionsWithMimatchingCommas() {
		assertThrows(
			IllegalArgumentException.class,
			() -> CsvParser.getLinePartitions("123\"AB\",0,,")
		);

		assertThrows(
			IllegalArgumentException.class,
			() -> CsvParser.getLinePartitions("123,\"AB\"C,0,,")
		);
	}

	@Test
	void testUnescape() {
		assertEquals("", CsvParser.unescape("\"\""));
		assertEquals("TXT", CsvParser.unescape("\"TXT\""));
		assertEquals("\"TXT\"", CsvParser.unescape("\"\"\"TXT\"\"\""));
		assertEquals("Conan, the \"Barbarian\"", CsvParser.unescape("\"Conan, the \"\"Barbarian\"\"\""));
		assertEquals("Nothing to escape here", CsvParser.unescape("Nothing to escape here"));
	}
}
