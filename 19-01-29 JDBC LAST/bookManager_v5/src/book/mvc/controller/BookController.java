package book.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;

import book.exception.BookException;
import book.mvc.model.service.BookService;
import book.mvc.model.vo.Book;
import book.mvc.view.BookMenu;

public class BookController {
	private BookService bService;
	public BookController() {
		try {
			bService = new BookService();
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void insertBook(Book book) {
		try {
			if(bService.insert(book) > 0) {
				System.out.println("새 도서 등록 성공 !");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void updateBook(Book book) {
		try {
			if(bService.update(book) > 0) {
				System.out.println("도서 정보 변경 성공!");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void deleteBook(int bookId) {
		try {
			if(bService.delete(bookId) > 0) {
				System.out.println("도서 정보 삭제 성공!");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void searchBook(int bookId) {
		try {
			Book book = bService.selectBook(bookId);
			new BookMenu().printBook(book);
			if(book != null) {
				System.out.println("도서 정보 조회 성공!");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void searchBookList(String title) {
		try {
			ArrayList<Book> bookList = bService.selectTitleList(title);
			new BookMenu().printList(bookList);
			if(bookList.size() > 0) {
				System.out.println("제목으로 조회 성공!");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void searchBookMap(String title) {
		try {
			HashMap<Integer, Book> bookMap = bService.selectTitleMap(title);
			new BookMenu().printMap(bookMap);
			if(bookMap.size() > 0) {
				System.out.println("제목으로 조회 성공!!!");
			}
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void selectAllList() {
		try {
			new BookMenu().printList(bService.selectList());
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	public void selectAllMap() {
		try {
			new BookMenu().printMap(bService.selectMap());
		} catch (Exception e) {
			new BookMenu().printError(e.getMessage());
			new BookMenu().displayMenu();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
