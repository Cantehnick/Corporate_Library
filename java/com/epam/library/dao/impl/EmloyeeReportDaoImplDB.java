package com.epam.library.dao.impl;

import static com.epam.library.dao.impl.util.DBConnectionHelper.connect;
import static com.epam.library.dao.impl.util.DBConnectionHelper.disconnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.EmployeeDaoReport;
import com.epam.library.domain.Report;

public class EmloyeeReportDaoImplDB implements EmployeeDaoReport {

	private int employeeId;
	private static final String SQL_REPORT_BOOK = "SELECT book.title, COUNT(employee_book.book_id) FROM book INNER JOIN employee_book ON book.id=employee_book.book_id WHERE employee_book.employee_id = ? GROUP BY book.title";
	private static final String SQL_EMPLOYEE_NAME = "SELECT employee.name FROM employee WHERE employee.id = ?";

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public List<Report> getListReport() {

		Connection connect = connect();

		List<Report> reportBook = new ArrayList<>();
		try (PreparedStatement ps = connect.prepareStatement(SQL_REPORT_BOOK)) {

			ps.setInt(1, employeeId);
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
	public String getEmployeeName() {

		Connection connect = connect();

		String title = "  ";
		try (PreparedStatement ps = connect.prepareStatement(SQL_EMPLOYEE_NAME)) {

			ps.setInt(1, employeeId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				title = rs.getString("title");
			}

			return title;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

}
