package contracts.factories;

import contracts.models.IModel;

public interface IModelFactory<T extends IModel> extends IFactory<T> {
	/**
	 * Create with id predefined
	 * @param id
	 * @return
	 */
	public T create(int id);
}
