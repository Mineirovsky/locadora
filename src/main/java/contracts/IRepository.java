package contracts;

import java.util.Collection;

import models.BaseModel;

/**
 * @author Gabriel Mineiro <gabrielpfgmineiro@gmail.com>
 *
 * @param <T> Model of the repository
 */
public interface IRepository<T extends BaseModel> {
	/**
	 * Get all elements in the repository
	 *
	 * @return All elements in the repository
	 */
	public Collection<T> all();

	/**
	 * Get an entity with given unique ID
	 *
	 * @param id
	 * @return The object with given ID, null if none matches 
	 */
	public T get(int id);

	/**
	 * Save entity to repository
	 *
	 * @param entity
	 * @return Give back the saved entity
	 */
	public T save(T entity);

	/**
	 * Remove entity from repository
	 *
	 * @param entity
	 * @return The removed entity, null if given entity was not found
	 */
	public T delete(T entity);
}
