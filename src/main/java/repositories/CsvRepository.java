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

	@Override
	public Collection<T> all() {
		if (items == null) {
			try {
				reload();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}

		return new LinkedList<T>(items);
	}

	public CsvRepository<T> reload() throws IOException {
		Scanner scanner = getScanner();
		items = new TreeSet<T>();

		while(scanner.hasNextLine()) {
			items.add(readLine(scanner));
		}

		return this;
	}
}
