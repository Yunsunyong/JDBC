package book.mvc.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import book.mvc.model.vo.Book;
import static common.JDBCTemp.*;

public class BookDao {
	public BookDao() {}

	public int insertBook(Connection conn, Book b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "insert into book values (seq_bid.nextval, ?, ?, ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setDate(4, b.getPublishDate());
			pstmt.setInt(5, b.getPrice());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBook(Connection conn, Book b) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "update book set price = ? where book_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getPrice());
			pstmt.setInt(2, b.getBookId());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBook(Connection conn, int bId) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = "delete from book where book_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}

	public Book selectBook(Connection conn, int bId) {
		Book b = new Book();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from book where book_id = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			
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
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public Book searchBookTitle(Connection conn, String bookTitle) {
		Book b = new Book();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "select * from book where title like ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + bookTitle +"%");
			rset = pstmt.executeQuery();
			
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
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public ArrayList<Book> selectAllBooks(Connection conn) {
		ArrayList<Book> bookList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = "select * from book";
		
		try {
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
			close(rset);
			close(stmt);
		}
		return bookList;
	}
	
}
