package book.mvc.controller;

import java.util.ArrayList;
import java.util.List;

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
		int result = bs.insertBook(b);
		
		if(result > 0) {
			System.out.println("책 등록 성공!");
		}else {
			System.out.println("\n새 책 등록 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
	}

	public void updateBook(Book b) {
		int result = bs.updateBook(b);
		
		if(result > 0) {
			System.out.println("\n책 내용 수정 완료!!");
		}else {
			System.out.println("\n책 내용 수정 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
	}

	public void deleteBook(int bId) {
		int result = bs.deleteBook(bId);
		
		if(result > 0) {
			System.out.println("\n책 삭제 완료!!");
		}else {
			System.out.println("\n책 삭제 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
		return;
	}

	public Book searchBook(int bId) {
		Book book = bs.selectBook(bId);
		
		if(book == null) {
			System.out.println(bId + "번호 책에 대한 정보가 없습니다.");
		}
		
		return book;
	}

	public Book searchBookTitle(String BookTitle) {
		Book book = bs.searchBookTitle(BookTitle);

		if (book == null) {
			System.out.println(BookTitle + " 책에 대한 정보가 없습니다.");
		}
		return book;
	}

	public List<Book> selectAll() {
		List<Book> bookList = bs.selectAllBooks();
		
		if(bookList.size() == 0) {
			System.out.println("책 정보가 없습니다.");
		}
		return bookList;
	}
}
