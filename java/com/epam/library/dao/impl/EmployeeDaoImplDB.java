package com.epam.library.dao.impl;

import static com.epam.library.dao.impl.util.DBConnectionHelper.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.epam.library.dao.EmployeeDao;
import com.epam.library.domain.Employee;

public  class EmployeeDaoImplDB implements EmployeeDao {

	private static final String SQL_SELECT_EMPLOYEES = "SELECT a.id, a.name, a.date_of_birth, a.email, COUNT(employee_book.employee_id) FROM employee AS a INNER JOIN employee_book ON a.id=employee_book.employee_id GROUP BY a.name ORDER BY COUNT(employee_book.employee_id) DESC";
	private static final String SQL_SELECT_EMPLOYEE = "SELECT * FROM employee WHERE id = ?";
	private static final String SQL_DELETE_EMPLOYEE = "DELETE FROM employee WHERE employee.id=?";
	private static final String SQL_UPDATE_EMPLOYEE = "UPDATE employee SET name=?, date_of_birth=?, email=? WHERE id=?";
	private static final String SQL_INSERT_EMPLOYEE = "INSERT INTO employee (name, date_of_birth, email) VALUES (?, ?, ?)";

	@Override
	public void create(Employee entity) {
		if (entity != null) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_INSERT_EMPLOYEE)) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date = entity.getBirthDate();
				String strDate = dateFormat.format(date);

				Calendar calendar = Calendar.getInstance();
				try {
					calendar.setTime(dateFormat.parse(strDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				calendar.add(Calendar.DATE, 1);
				strDate = dateFormat.format(calendar.getTime());
				java.sql.Date sqlDate = java.sql.Date.valueOf(strDate);

				ps.setString(1, entity.getName());
				ps.setDate(2, sqlDate);
				ps.setString(3, entity.getEmail());

				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			disconnect(connect);
		}

	}

	@Override
	public Employee read(int id) {
		if (id != 0) {
			Connection connect = connect();

			Employee employee = new Employee();
			try (PreparedStatement ps = connect.prepareStatement(SQL_SELECT_EMPLOYEE)) {

				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {

					employee.setId(rs.getInt(1));
					employee.setName(rs.getString("name"));
					employee.setBirthDate(rs.getDate(3));
					employee.setEmail(rs.getString("email"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return employee;
		}
		return null;
	}

	@Override
	public void update(Employee entity) {
		if (entity != null) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_UPDATE_EMPLOYEE)) {

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
				Date date = entity.getBirthDate();
				String strDate = dateFormat.format(date);

				Calendar calendar = Calendar.getInstance();
				try {
					calendar.setTime(dateFormat.parse(strDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				calendar.add(Calendar.DATE, 1);
				strDate = dateFormat.format(calendar.getTime());
				java.sql.Date sqlDate = java.sql.Date.valueOf(strDate);

				ps.setString(1, entity.getName());
				ps.setDate(2, sqlDate);
				ps.setString(3, entity.getEmail());
				ps.setInt(4, entity.getId());

				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			disconnect(connect);
		}
	}

	@Override
	public void delete(int id) {
		if (id != 0) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_DELETE_EMPLOYEE)) {

				ps.setInt(1, id);

				ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			disconnect(connect);
		}
	}

	
	public List<Employee> readAll() {
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
				employee.setTakenBooksAmount(rs.getInt(5));
				employees.add(employee);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		disconnect(connect);
		return employees;
	}

}
