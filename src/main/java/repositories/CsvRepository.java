package repositories;

import java.util.SortedSet;
import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import contracts.repositories.IRepository;
import helpers.CsvParser;
import models.BaseModel;

public abstract class CsvRepository<T extends BaseModel> implements IRepository<T> {
	protected IStorage storage;
	protected IModelFactory<T> factory;
	CsvParser<T> parser;
	protected SortedSet<T> items;

	public CsvRepository(IStorage storage, IModelFactory<T> factory, CsvParser<T> parser) {
		this.storage = storage;
		this.factory = factory;
		this.parser = parser;
	}

	abstract protected String getName();

	protected String getFileName() {
		return getName() + ".csv";
	}
}
