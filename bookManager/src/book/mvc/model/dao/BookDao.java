package book.mvc.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import book.mvc.model.vo.Book;

public class BookDao {
	public BookDao() {}

	public int insertBook(Book b) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "insert into book values (seq_bid.nextval, '"
				+ b.getTitle()+ "', '"
				+ b.getAuthor()+"', '"
				+ b.getPublisher()+"', '"
				+ b.getPublishDate()+"', "
				+ b.getPrice()+")";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe",
					"student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int updateBook(Book b) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "update book set price = "
				+ b.getPrice()+" where book_id = "
				+ b.getBookId()+"";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int deleteBook(int bId) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		
		String query = "delete from book where book_id = "
				+ bId+"";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			result = stmt.executeUpdate(query);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return result;
	}

	public Book selectBook(int bId) {
		Book b = new Book();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from book where book_id = "
				+ bId+"";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public Book searchBookTitle(String bookTitle) {
		Book b = new Book();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from book where title like '%"
				+ bookTitle+"%' ";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(rset.next()) {
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(bookTitle);
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public ArrayList<Book> selectAllBooks() {
		ArrayList<Book> bookList = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from book";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "student", "student");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				Book b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
				
				bookList.add(b);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}
	
}
