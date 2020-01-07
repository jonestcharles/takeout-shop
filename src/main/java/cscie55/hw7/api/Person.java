package cscie55.hw7.api;

import cscie55.hw7.impl.Address;

public abstract class Person {

	private String firstName;

	private String lastName;

	private Address address;

	public Person(String firstName, String lastName, Address address){
		this.firstName = firstName;
		this.lastName = lastName;
        this.address = address;
	}

	/**
	 * The first name of the Person. Note: No setter. One you get a first name, it sticks!
	 * @return - a string returning the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * The first name of the Person. Note: There is no setter. One you get a last name, it sticks!
	 * @return - a string returning the last name
	 */
	public String getLastName() {
		return lastName;
	}

	public Address getAddress() {
		return address;
	}

	/**
	 * THe address can be set. We will allow you to move.
	 * @param address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
}
