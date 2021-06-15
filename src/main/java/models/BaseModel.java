package models;

/**
 * @author Gabriel Mineiro <gabrielpfgmineiro@gmail.com>
 *
 */
public abstract class BaseModel implements Comparable<BaseModel> {
	/**
	 * Unique identifier
	 */
	private int id;

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
}
