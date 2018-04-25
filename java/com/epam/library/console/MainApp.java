package com.epam.library.console;

import java.util.List;

import com.epam.library.dao.BookDao;
import com.epam.library.dao.impl.BookDaoImplDB;
import com.epam.library.domain.Book;


public class MainApp {

	public static void main(String[] args) {
		BookDao bookDao=new BookDaoImplDB();
		List<Book> books = bookDao.readAll();
	for (Book book : books) {
		System.out.println(book);
	}
	}

}
