package book.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import book.exception.BookException;
import book.mvc.model.dao.BookDao;
import book.mvc.model.service.BookService;
import book.mvc.model.vo.Book;
import book.mvc.view.BookMenu;

public class BookController {
	//DI
	//private BookDao bdao = new BookDao();
	private BookService bs = new BookService();
	//기본생성자
	public BookController() {}

	public void insertBook(Book b) {
		try {
			int result = bs.insertBook(b);
			if(result > 0) {
				System.out.println("책 등록 성공!");
			}
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}

	public void updateBook(Book b) {
		try {
			int result = bs.updateBook(b);
			if(result > 0) {
				System.out.println("\n책 내용 수정 완료!!");
			}
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}
		
		
	}

	public void deleteBook(int bId) {
		try {
			int result = bs.deleteBook(bId);
			if(result > 0) {
				System.out.println("\n책 삭제 완료!!");
			}
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}
		return;
	}

	public Book searchBook(int bId) {
		Book book = null;
		try {
			book = bs.selectBook(bId);
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}		
		return book;
	}

	public Book searchBookTitle(String BookTitle) {
		Book book = null;
		try {
			book = bs.searchBookTitle(BookTitle);
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}
		return book;
	}

	public HashMap<Integer,Book> selectAll() {
		HashMap<Integer, Book> bookMap = null;
		try {
			bookMap = bs.selectAllBooks();
		} catch (BookException e) {
			new BookMenu().displayError(e.getMessage());
			new BookMenu().displayMenu();
		}
		return bookMap;
	}
}
