package factories.models;

import models.Customer;

public class CustomerFactory extends ModelFactory<Customer> {
	@Override
	public Customer create() {
		return new Customer();
	}
}
