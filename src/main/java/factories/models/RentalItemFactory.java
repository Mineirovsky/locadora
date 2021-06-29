package factories.models;

import models.RentalItem;

public class RentalItemFactory extends ModelFactory<RentalItem> {
	@Override
	public RentalItem create() {
		return new RentalItem();
	}
}
