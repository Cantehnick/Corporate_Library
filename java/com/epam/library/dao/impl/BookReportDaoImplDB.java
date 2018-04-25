package com.epam.library.dao.impl;

import static com.epam.library.dao.impl.util.DBConnectionHelper.connect;
import static com.epam.library.dao.impl.util.DBConnectionHelper.disconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDaoReport;
import com.epam.library.domain.Report;

public class BookReportDaoImplDB implements BookDaoReport {

	private int bookId;
	private static final String SQL_REPORT_BOOK = "SELECT employee.name, COUNT(employee_book.employee_id) FROM employee INNER JOIN employee_book ON employee.id=employee_book.employee_id WHERE employee_book.book_id = ? GROUP BY employee.name";
	private static final String SQL_BOOK_TITLE = "SELECT book.title FROM book WHERE book.id = ?";

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@Override
	public List<Report> getListReport() {

		Connection connect = connect();

		List<Report> reportBook = new ArrayList<>();
		try (PreparedStatement ps = connect.prepareStatement(SQL_REPORT_BOOK)) {

			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Report report = new Report();

				report.setName(rs.getString("name"));
				report.setAmoutBooks(rs.getInt(2));

				reportBook.add(report);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		disconnect(connect);
		return null;
	}
	
	
	@Override
	public String getBookTitle() {
		Connection connect = connect();

		String name = "  ";
		try (PreparedStatement ps = connect.prepareStatement(SQL_BOOK_TITLE)) {

			ps.setInt(1, bookId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				name = rs.getString("name");
			}

			return name;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
