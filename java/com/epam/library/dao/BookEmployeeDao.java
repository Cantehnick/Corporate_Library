package com.epam.library.dao;

import java.util.List;

import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;

public interface BookEmployeeDao {

	void addBookEmployee(int bookId, int employeeId);

	List<Book> getBooks();

	List<Employee> getEmployees();
}
