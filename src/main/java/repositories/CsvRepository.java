package repositories;

import contracts.drivers.IStorage;
import contracts.repositories.IRepository;
import models.BaseModel;

public abstract class CsvRepository<T extends BaseModel> implements IRepository<T> {
	protected IStorage storage;

	public CsvRepository(IStorage storage) {
		this.storage = storage;
	}
	
	protected abstract String getName();
	
	protected String getFileName() {
		return getName() + ".csv";
	}
}
