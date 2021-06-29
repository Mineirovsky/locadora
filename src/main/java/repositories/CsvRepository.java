package repositories;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import contracts.repositories.IRepository;
import helpers.CsvParser;
import models.BaseModel;
import models.ModelBuilder;

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

	@Override
	public T save(T entity) {
		ModelBuilder<T> builder;
		T newEntity;

		// Make sure the repository is loaded
		if (all() == null) {
			System.exit(-1);
		}

		// Newly created entity
		if (entity.getId() == 0) {
			builder = new ModelBuilder<T>(entity);

			try {
				builder.set("id", getNewId());
				newEntity = builder.build();
				items.add(newEntity);
				return newEntity;
			} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
				System.exit(-1);
			}
		}

		T found = get(entity.getId());

		if (found == null) {
			throw new IllegalArgumentException("Trying to update an absent entity");
		}

		items.remove(found);
		items.add(entity);

		return entity;
	}

	public CsvRepository<T> reload() throws IOException {
		Scanner scanner = getScanner();
		items = new TreeSet<T>();

		while(scanner.hasNextLine()) {
			items.add(readLine(scanner));
		}

		return this;
	}

	protected T readLine(Scanner scanner) {
		return parser.parseLine(scanner.nextLine());
	}

	protected Scanner getScanner() throws IOException {
		File file = storage.file(getFileName());

		if (!file.exists()) {
			file.createNewFile();
		}
		return new Scanner(file);
	}

	protected CsvRepository<T> store() throws IOException {
		storage.saveContents(getFileName(), serializeItems());
		return this;
	}

	private String serializeItems() {
		StringBuilder stringBuilder = new StringBuilder();
		Iterator<T> it = items.iterator();

		while (it.hasNext()) {
			stringBuilder.append(it.next().toCsv());
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}

	private int getNewId() {
		if (items.size() == 0) {
			return 1;
		}
		return items.last().getId() + 1;
	}
}
