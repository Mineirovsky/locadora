package app;

import drivers.LocalFileStorage;
import factories.models.CustomerFactory;
import factories.models.MovieFactory;
import factories.models.RentalFactory;
import factories.models.RentalItemFactory;
import helpers.CsvParser;
import models.Customer;
import models.Movie;
import models.Rental;
import models.RentalItem;
import repositories.CustomerRepository;
import repositories.MovieRepository;
import repositories.RentalItemRepository;
import repositories.RentalRepository;

public class Repository {
	private LocalFileStorage storage;

	private CustomerRepository customers;
	private MovieRepository movies;
	private RentalRepository rentals;
	private RentalItemRepository rentalItems;

	private CustomerFactory customerFactory;
	private MovieFactory movieFactory;
	private RentalFactory rentalFactory;
	private RentalItemFactory rentalItemFactory;

	public Repository() {
		storage = new LocalFileStorage();
		initFactories();
		initRepositories();
	}

	private void initFactories() {
		customerFactory = new CustomerFactory();
		movieFactory = new MovieFactory();
		rentalFactory = new RentalFactory();
		rentalItemFactory = new RentalItemFactory();
	}

	private void initRepositories() {
		customers = new CustomerRepository(storage, customerFactory, new CsvParser<Customer>(customerFactory));
		movies = new MovieRepository(storage, movieFactory, new CsvParser<Movie>(movieFactory));
		rentals = new RentalRepository(storage, rentalFactory, new CsvParser<Rental>(rentalFactory));
		rentalItems = new RentalItemRepository(storage, rentalItemFactory, new CsvParser<RentalItem>(rentalItemFactory));
	}

	public CustomerRepository customers() {
		return customers;
	}

	public MovieRepository movies() {
		return movies;
	}

	public RentalRepository rentals() {
		return rentals;
	}

	public RentalItemRepository rentalItems() {
		return rentalItems;
	}
}
