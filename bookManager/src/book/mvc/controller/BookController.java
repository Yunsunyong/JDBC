package book.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import book.mvc.model.dao.BookDao;
import book.mvc.model.vo.Book;

public class BookController {
	//DI
	private BookDao bdao = new BookDao();
	
	//기본생성자
	public BookController() {}

	public void insertBook(Book b) {
		int result = bdao.insertBook(b);
		
		if(result > 0) {
			System.out.println("\n책 등록 성공!!");
		}else {
			System.out.println("\n새 책 등록 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
	}

	public void updateBook(Book b) {
		int result = bdao.updateBook(b);
		
		if(result > 0) {
			System.out.println("\n책 내용 수정 완료!!");
		}else {
			System.out.println("\n책 내용 수정 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
	}

	public void deleteBook(int bId) {
		int result = bdao.deleteBook(bId);
		
		if(result > 0) {
			System.out.println("\n책 삭제 완료!!");
		}else {
			System.out.println("\n책 삭제 실패...!!");
			System.out.println("확인하고 다시 시도하십시오.");
		}
		return;
	}

	public Book searchBook(int bId) {
		Book book = bdao.selectBook(bId);
		
		if(book == null) {
			System.out.println(bId + "번호 책에 대한 정보가 없습니다.");
		}
		
		return book;
	}

	public Book searchBookTitle(String BookTitle) {
		Book book = bdao.searchBookTitle(BookTitle);

		if (book == null) {
			System.out.println(BookTitle + " 책에 대한 정보가 없습니다.");
		}
		return book;
	}

	public List<Book> selectAll() {
		List<Book> bookList = bdao.selectAllBooks();
		
		if(bookList.size() == 0) {
			System.out.println("책에 대한 정보가 없습니다.");
		}
		
		return bookList;
	}
}
