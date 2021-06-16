package models;

import java.time.LocalDate;

public class Customer extends BaseModel {
	public String firstName;
	public String lastName;
	public LocalDate birthday;
	public String addressLine1;
	public String addressLine2;
	public String city;
	public String state;
	public String phone;
}
