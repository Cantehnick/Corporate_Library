package com.epam.library.domain;

import java.util.Date;

public class Employee extends Entity {

	private String name;
	private String email;
	private Date birthDate;

	private int takenBooksAmount;

	public Employee() {
		super();
	}

	public Employee(int id) {
		super(id);
	}

	public Employee(String name, String email, Date birthDate, int takenBooksAmount) {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.takenBooksAmount = takenBooksAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getTakenBooksAmount() {
		return takenBooksAmount;
	}

	public void setTakenBooksAmount(int takenBooksAmount) {
		this.takenBooksAmount = takenBooksAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Emloyee [name=" + name + ", email=" + email + ", birthDate=" + birthDate + "]";
	}

}
