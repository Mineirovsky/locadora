package repositories;

import contracts.drivers.IStorage;
import contracts.factories.IModelFactory;
import helpers.CsvParser;
import models.Customer;

public class CustomerRepository extends CsvRepository<Customer> {
	public CustomerRepository(IStorage storage, IModelFactory<Customer> factory, CsvParser<Customer> parser) {
		super(storage, factory, parser);
	}

	@Override
	protected String getName() {
		return "customers";
	}
}
