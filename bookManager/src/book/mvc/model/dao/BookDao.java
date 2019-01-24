package book.mvc.model.dao;

import java.sql.Connection;
import java.sql.Statement;

import book.mvc.model.vo.Book;

public class BookDao {
	public BookDao() {}

	public int insertBook(Book b) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String q
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			}
		}
		
		return result;
	}
}
