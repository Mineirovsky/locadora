package models;

import java.lang.reflect.Field;

import contracts.models.IModelBuilder;

public class ModelBuilder<T extends BaseModel> implements IModelBuilder<T> {
	private T model;

	public ModelBuilder (T model) {
		this.model = model;
	}

	@Override
	public IModelBuilder<T> set(String fieldName, Object value)
		throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
	{
		if (fieldName.equals("id")) {
			model.setId(Integer.getInteger(value.toString()));
		}

		Field field = findField(fieldName);

		if (field == null) {
			throw new NoSuchFieldException(fieldName + " is not a member of the model " + model.getClass().getName());
		}

		field.set(model, value);

		return this;
	}

	@Override
	public T build() {
		return model;
	}

	private Field findField(String fieldName) {
		for (Field field: model.getFields()) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}

		return null;
	}

	@Override
	public final String[] getFields() {
		Field[] fields = model.getFields();
		String[] fieldNames = new String[fields.length];

		for (int i = 0; i < fields.length; i++) {
			fieldNames[i] = fields[i].getName();
		}

		return fieldNames;
	}

	@Override
	public String getFieldType(String fieldName) {
		Field field = findField(fieldName);

		if (field == null) {
			return null;
		}

		return field.getAnnotatedType().toString();
	}
}
