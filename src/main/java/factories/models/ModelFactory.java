package factories.models;

import contracts.factories.IModelFactory;
import models.BaseModel;
import models.ModelBuilder;

public abstract class ModelFactory<T extends BaseModel> implements IModelFactory<T> {
	public ModelBuilder<T> getBuilder() {
		return new ModelBuilder<T>(create());
	}

	@Override
	public T create(int id) {
		ModelBuilder<T> builder = getBuilder();

		try {
			builder.set("id", id);
		} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			// No need to handle these exceptions
		}

		return builder.build();
	}
}
