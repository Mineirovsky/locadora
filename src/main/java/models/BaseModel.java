package models;

/**
 * @author Gabriel Mineiro <gabrielpfgmineiro@gmail.com>
 *
 */
public abstract class BaseModel {
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
}
