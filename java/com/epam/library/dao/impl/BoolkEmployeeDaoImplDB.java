package com.epam.library.dao.impl;

import static com.epam.library.dao.impl.util.DBConnectionHelper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookEmployeeDao;
import com.epam.library.domain.Book;
import com.epam.library.domain.Employee;




public class BoolkEmployeeDaoImplDB implements BookEmployeeDao {

	private static final String SQL_BOOK_EMPLOYEE = "INSERT INTO employee_book (book_id, employee_id) VALUES (?, ?)";
	private static final String SQL_SELECT_BOOKS = "SELECT * FROM book ORDER BY id DESC";
	private static final String SQL_SELECT_EMPLOYEES = "SELECT * FROM employee ORDER BY id DESC";


	@Override
	public void addBookEmployee(int bookId, int employeeId) {
		if (bookId != 0 && employeeId != 0) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_BOOK_EMPLOYEE)) {

				ps.setInt(1, bookId);
				ps.setInt(2, employeeId);

				ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			disconnect(connect);
		}
	}

	@Override
	public List<Book> getBooks() {
		Connection connect = connect();
		List<Book> books = new ArrayList<>();

		try (PreparedStatement ps = connect.prepareStatement(SQL_SELECT_BOOKS)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setTitle(rs.getString("title"));
				book.setBrief(rs.getString("brief"));
				book.setPublishYear(rs.getInt("publish_year"));
				book.setAuthor(rs.getString("author"));
				books.add(book);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		disconnect(connect);
		return books;
	}

	@Override
	public List<Employee> getEmployees() {
	
		Connection connect = connect();
		List<Employee> employees = new ArrayList<>();

		try (PreparedStatement ps = connect.prepareStatement(SQL_SELECT_EMPLOYEES)) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee employee = new Employee();
				
				employee.setId(rs.getInt(1));
				employee.setName(rs.getString("name"));
				employee.setBirthDate(rs.getDate(3));
				employee.setEmail(rs.getString("email"));
				employees.add(employee);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		disconnect(connect);
		return employees;
	}




}