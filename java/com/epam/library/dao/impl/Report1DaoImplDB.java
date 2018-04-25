package com.epam.library.dao.impl;

import static com.epam.library.dao.impl.util.DBConnectionHelper.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BaseDaoReport;
import com.epam.library.domain.Report;

public class Report1DaoImplDB implements BaseDaoReport {

	private static final String SQL_REPORT_1 = "SELECT employee.id, employee.name, COUNT(employee_book.employee_id) FROM employee INNER JOIN employee_book ON employee.id = employee_book.employee_id GROUP BY employee.name HAVING COUNT(employee_book.employee_id) > 2 ORDER BY COUNT(employee_book.employee_id) DESC";

	@Override
	public List<Report> getListReport() {

		Connection connect = connect();

		List<Report> report1 = new ArrayList<>();
		try (PreparedStatement ps = connect.prepareStatement(SQL_REPORT_1)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Report report = new Report();

				report.setId(rs.getInt("id"));
				report.setName(rs.getString("name"));
				report.setAmoutBooks(rs.getInt(3));

				report1.add(report);
			}
			return report1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
