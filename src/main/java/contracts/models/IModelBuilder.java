package contracts.models;

public interface IModelBuilder<T extends IModel> {
	/**
	 * Set a field in the model
	 *
	 * @param field
	 * @param value
	 * @return
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 */
	public IModelBuilder<T> set (String field, Object value) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException;

	/**
	 * Build the model instance
	 * @return Created model instance
	 */
	public T build();

	/**
	 * Get the model's fields name in the preferred order 
	 * @return List of fields names
	 */
	public String[] getFields();

	/**
	 * Get type name of field
	 * @param field
	 * @return Type name of field, null if field was not found
	 */
	public String getFieldType(String field);
}
