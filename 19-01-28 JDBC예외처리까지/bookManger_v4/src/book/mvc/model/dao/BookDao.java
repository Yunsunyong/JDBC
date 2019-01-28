package book.mvc.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import book.exception.BookException;
import book.mvc.model.vo.Book;
import static common.JDBCTemp.*;

public class BookDao {
	//DI
	private Properties prop = new Properties();
	
	public BookDao() {
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int insertBook(Connection conn, Book b) throws BookException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("insert");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getAuthor());
			pstmt.setString(3, b.getPublisher());
			pstmt.setDate(4, b.getPublishDate());
			pstmt.setInt(5, b.getPrice());
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException("책 등록 실패!!!");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateBook(Connection conn, Book b) throws BookException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("updateb");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getPrice());
			pstmt.setInt(2, b.getBookId());
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException(b.getBookId() + " 번 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteBook(Connection conn, int bId) throws BookException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException(bId + " 번의 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			rollback(conn);
			throw new BookException(e.getMessage());
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Book selectBook(Connection conn, int bId) throws BookException{
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectb");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
			}
			
			if(b == null) {
				throw new BookException(bId + " 번의 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public Book searchBookTitle(Connection conn, String bookTitle) throws BookException{
		Book b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectbt");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + bookTitle +"%");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(bookTitle);
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
			}
			
			if(b == null) {
				throw new BookException(bookTitle + " 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public HashMap<Integer,Book> selectAllBooks(Connection conn) throws BookException{
		HashMap<Integer,Book> bookMap = new HashMap<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectall");
		
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
				
				bookMap.put(b.getBookId(), b);
			}
			
			if(bookMap.size() == 0) {
				throw new BookException("책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}
		return bookMap;
	}
	
}
