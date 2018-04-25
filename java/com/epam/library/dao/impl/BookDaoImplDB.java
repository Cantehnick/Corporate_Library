package com.epam.library.dao.impl;


import static com.epam.library.dao.impl.util.DBConnectionHelper.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.epam.library.dao.BookDao;
import com.epam.library.domain.Book;

public  class BookDaoImplDB implements BookDao {

	private static final String SQL_SELECT_BOOKS = "SELECT * FROM book";
	private static final String SQL_INSERT_BOOK = "INSERT INTO book(title,brief,author,publish_year) VALUES (?, ?, ?, ?)";
	private static final String SQL_DELETE_BOOK = "DELETE FROM book WHERE book.id=?";
	private static final String SQL_SELECT_BOOK = "SELECT * FROM book WHERE id = ?";
	private static final String SQL_UPDATE_BOOK = "UPDATE book SET title=?, brief=?, publish_year=?, author=? WHERE id=?";

	public void create(Book entity) {
		if (entity != null) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_INSERT_BOOK)) {

				ps.setString(1, entity.getTitle());
				ps.setString(2, entity.getBrief());
				ps.setString(3, entity.getAuthor());
				ps.setInt(4, entity.getPublishYear());

				ps.executeUpdate();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			disconnect(connect);
		}
	}

	public Book read(int id) {
		if (id != 0) {
			Connection connect = connect();
		
			Book book = new Book();
			try (PreparedStatement ps = connect.prepareStatement(SQL_SELECT_BOOK)) {

				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
				
					book.setId(rs.getInt(1));
					book.setTitle(rs.getString("title"));
					book.setTitle(rs.getString("brief"));
					book.setPublishYear(rs.getInt("publish_year"));
					book.setAuthor(rs.getString("author"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			disconnect(connect);
			return book;
		}
		return null;
	}

	public void update(Book entity) {

		if (entity != null) {

			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_UPDATE_BOOK);) {

				ps.setString(1, entity.getTitle());
				ps.setString(2, entity.getBrief());
				ps.setInt(3, entity.getPublishYear());
				ps.setString(4, entity.getAuthor());
				ps.setInt(5, entity.getId());

				ps.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			disconnect(connect);

		}
	}

	public void delete(int id) {
		if (id != 0) {
			Connection connect = connect();

			try (PreparedStatement ps = connect.prepareStatement(SQL_DELETE_BOOK)) {

				ps.setInt(1, id);

				ps.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}
			disconnect(connect);
		}
	}

	public List<Book> readAll() {
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

}
