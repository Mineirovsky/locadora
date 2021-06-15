package models;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeMap;

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
	
	private SortedMap<String, Field> fields;
	
	BaseModel() {
		setupFields();
	}

	private void setupFields() {
		Field[] fields = this.getClass().getFields();
		this.fields = new TreeMap<String, Field>();
		
		for (Field field: fields) {
			this.fields.put(field.getName(), field);
		}
		
	}
	
	public int getId() {
		return id;
	}
	
	void setId(int id) {
		this.id = id;
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
		String csv = "" + id;
		String field;

		Iterator<Field> it = fields.values().iterator();
		
		while(it.hasNext()) {
			try {
				field = it.next().get(this).toString();
				csv += "," + Csv.escape(field);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}

		return csv;
	}
}
