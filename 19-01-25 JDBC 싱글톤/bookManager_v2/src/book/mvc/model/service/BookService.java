package book.mvc.model.service;

import java.sql.*;
import java.util.*;
import book.mvc.model.dao.BookDao;
import book.mvc.model.vo.Book;
import static common.JDBCTemp.*;

public class BookService {
	private BookDao bdao = new BookDao();
	
	public BookService() {}

	public int insertBook(Book b) {
		Connection conn = getConnection();
		int result = bdao.insertBook(conn,b);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}
	

	public int updateBook(Book b) {
		Connection conn = getConnection();
		int result = bdao.updateBook(conn, b);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}

		return result;
	}

	public int deleteBook(int bId) {
		Connection conn = getConnection();
		int result = bdao.deleteBook(conn, bId);
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		return result;
	}

	public Book selectBook(int bId) {
		Connection conn = getConnection();
		Book b = bdao.selectBook(conn, bId);
		close(conn);
		return b;
	}

	public Book searchBookTitle(String bookTitle) {
		Connection conn = getConnection();
		Book b = bdao.searchBookTitle(conn, bookTitle);
		close(conn);
		return b;
	}

	public List<Book> selectAllBooks() {
		Connection conn = getConnection();
		List<Book> list = bdao.selectAllBooks(conn);
		close(conn);
		return list;
	}


	
}
