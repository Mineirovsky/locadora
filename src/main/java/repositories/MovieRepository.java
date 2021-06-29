package repositories;

import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import helpers.CsvParser;
import models.Movie;

public class MovieRepository extends CsvRepository<Movie> {
	public MovieRepository(IStorage storage, IModelFactory<Movie> factory, CsvParser<Movie> parser) {
		super(storage, factory, parser);
	}

	@Override
	protected String getName() {
		return "movies";
	}
}
