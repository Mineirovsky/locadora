package factories.models;

import models.Rental;

public class RentalFactory extends ModelFactory<Rental> {
	@Override
	public Rental create() {
		return new Rental();
	}
}
