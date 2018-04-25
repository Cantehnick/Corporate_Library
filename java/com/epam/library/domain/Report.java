package com.epam.library.domain;

import java.sql.Date;

public class Report extends Entity {

	private int id;
	private String name;
	private Date birthDate;
	private int amoutBooks;

	public Report() {
		super();
	}

	public Report(int id, String name, Date birthDate, int amoutBooks) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.amoutBooks = amoutBooks;
	}

	public Report(int id) {
		super(id);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAmoutBooks() {
		return amoutBooks;
	}

	public void setAmoutBooks(int amoutBooks) {
		this.amoutBooks = amoutBooks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amoutBooks;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (amoutBooks != other.amoutBooks)
			return false;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (id != other.id)
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
		return "Report [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", amoutBooks=" + amoutBooks + "]";
	}

}
