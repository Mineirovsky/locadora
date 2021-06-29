package repositories;

import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import helpers.CsvParser;
import models.RentalItem;

public class RentalItemRepository extends CsvRepository<RentalItem> {
	public RentalItemRepository(IStorage storage, IModelFactory<RentalItem> factory, CsvParser<RentalItem> parser) {
		super(storage, factory, parser);
	}

	@Override
	protected String getName() {
		return "rental_items";
	}
}
