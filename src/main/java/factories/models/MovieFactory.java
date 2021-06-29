package factories.models;

import models.Movie;

public class MovieFactory extends ModelFactory<Movie> {
	@Override
	public Movie create() {
		return new Movie();
	}
}
