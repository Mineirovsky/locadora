package contracts.factories;

import contracts.models.IModel;
import contracts.models.IModelBuilder;

public interface IModelFactory<T extends IModel> extends IFactory<T> {
	/**
	 * Create with id predefined
	 * @param id
	 * @return
	 */
	public T create(int id);
	
	/**
	 * Get new builder instance for the model
	 * @return
	 */
	public IModelBuilder<T> getBuilder();
}
