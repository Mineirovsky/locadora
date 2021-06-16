package models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TestCustomer {

	@Test
	void testToCsv() {
		Customer customer = new Customer();
		customer.setId(22);
		customer.addressLine1 = "Av. Ipiranga, 1101";
		customer.addressLine2 = "Centro";
		customer.birthday = LocalDate.of(2000, 1, 1);
		customer.city = "Vitoria";
		customer.firstName = "Gabriel";
		customer.lastName = "Mineiro";
		customer.phone = "+5527999001122";
		customer.state = "ES";

		System.out.println(customer.toCsv());

		assertEquals("22,\"Av. Ipiranga, 1101\",Centro,2000-01-01,Vitoria,Gabriel,Mineiro,+5527999001122,ES", customer.toCsv());
	}
}
