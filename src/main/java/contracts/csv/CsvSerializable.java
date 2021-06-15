package contracts.csv;

/**
 * For classes that can be serialized as CSV
 * @author Gabriel Mineiro <gabrielpfgmineiro@gmail.com>
 *
 */
public interface CsvSerializable {
	/**
	 * Create a CSV String with class contents
	 *
	 * @return CSV string
	 */
	public String toCsv();
}
