package book.mvc.controller;

import book.mvc.model.dao.BookDao;
import book.mvc.model.vo.Book;

public class BookController {
	//DI
	private BookDao bdao = new BookDao();
	
	//기본생서자
	public BookController() {}

	public void insertBook(Book b) {
		int result = bdao.insertBook(b);
		
	}
}
