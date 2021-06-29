package repositories;

import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import helpers.CsvParser;
import models.Rental;

public class RentalRepository extends CsvRepository<Rental> {
	public RentalRepository(IStorage storage, IModelFactory<Rental> factory, CsvParser<Rental> parser) {
		super(storage, factory, parser);
	}

	@Override
	protected String getName() {
		return "rentals";
	}
}
