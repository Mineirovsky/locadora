package contracts.factories;

public interface IFactory<T> {
	/**
	 * Create the most basic instance possible
	 * @return
	 */
	public T create();
}
