package models;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import contracts.csv.CsvSerializable;
import contracts.models.IModel;
import helpers.Csv;

/**
 * @author Gabriel Mineiro <gabrielpfgmineiro@gmail.com>
 *
 */
public abstract class BaseModel implements IModel, Comparable<BaseModel>, CsvSerializable {
	/**
	 * Unique identifier
	 */
	private int id = 0;

	private Field[] fields;

	public BaseModel() {
		setupFields();
	}

	private void setupFields() {
		fields = this.getClass().getFields();
		Arrays.sort(fields, new Comparator<Field>() {
			@Override
			public int compare(Field o1, Field o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	public int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}
	
	final Field[] getFields() {
		return fields;
	}

	public int compareTo(BaseModel o) {
		if (id < o.id) {
			return -1;
		}

		if (id > o.id) {
			return 1;
		}

		return 0;
	}

	public boolean equals(BaseModel obj) {
		return id == obj.id;
	}

	public String toCsv() {
		StringBuilder csv = new StringBuilder(Integer.toString(id));
		Object value;

		for (Field field: fields) {
			try {
				value = field.get(this);
				value = value != null ? value : "";
				csv.append(",");
				csv.append(Csv.escape(value.toString()));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return csv.toString();
	}
}
