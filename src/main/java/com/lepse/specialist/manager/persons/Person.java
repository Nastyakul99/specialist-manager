package com.lepse.specialist.manager.persons;

import java.util.Objects;

public class Person implements IPerson {

	private final String firstName;
	private final String lastName;
	private final String patronymic;

	public Person(String firstName, String lastName, String patronymic) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.patronymic = patronymic;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public String getPatronymic() {
		return patronymic;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, patronymic);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(patronymic, other.patronymic);
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + patronymic;
	}
}
