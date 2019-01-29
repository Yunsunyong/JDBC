package book.mvc.model.dao;

import java.io.FileReader;
import java.util.*;
import java.sql.*;

import book.exception.BookException;
import book.mvc.model.vo.Book;
import static common.JDBCTemp.*;

public class BookDao {
	private Properties prop = new Properties();
	
	public BookDao() throws BookException{
		try {
			prop.load(new FileReader("properties/query.properties"));
		} catch (Exception e) {
			throw new BookException(e.getMessage());
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
				throw new BookException("새로운 책 등록 실패 !");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(conn);
		}
		return result;
	}
	
	public int updateBook(Connection conn, Book b) throws BookException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("update");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, b.getPrice());
			pstmt.setInt(2, b.getBookId());
			
			result = pstmt.executeUpdate();
			
			if(result <= 0) {
				rollback(conn);
				throw new BookException(b.getBookId() + "번 책의 금액 수정 실패!!!");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage()); 
		} finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteBook(Connection conn, int bookId) throws BookException{
		int result = 0;
		PreparedStatement pstmt = null;
		
		String query = prop.getProperty("delete");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			
			result = pstmt.executeUpdate();
			if(result <= 0) {
				rollback(conn);
				throw new BookException(bookId + "번의 책 삭제 실패!!!");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(conn);
		}
		return result;
	}
	
	public ArrayList<Book> selectList(Connection conn) throws BookException{
		ArrayList<Book> bookList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		
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
			
			if(bookList.size() == 0) {
				rollback(conn);
				throw new BookException("조회할 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(stmt);
		}
		return bookList;
	}
	
	public HashMap<Integer, Book> selectMap(Connection conn) throws BookException{
		HashMap<Integer, Book> bookMap = new HashMap<>();
		Statement stmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectAll");
		
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
				rollback(conn);
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
	
	public ArrayList<Book> selectTitleList(Connection conn, String title) throws BookException{
		ArrayList<Book> bookList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+title+"%");
			rset = pstmt.executeQuery();
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
			
			if(bookList.size() == 0) {
				rollback(conn);
				throw new BookException(title + "이 쓴 책은 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookList;
	}

	public HashMap<Integer, Book> selectTitleMap(Connection conn, String title) throws BookException{
		HashMap<Integer, Book> bookMap = new HashMap<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectTitle");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+title+"%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Book b = new Book();
				b.setBookId(rset.getInt("book_id"));
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
				
				bookMap.put(b.getBookId(),b);
			}
			
			if(bookMap.size() == 0) {
				rollback(conn);
				throw new BookException(title + "이 쓴 책은 존재하지 않습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return bookMap;
	}
	
	public Book selectBook(Connection conn, int bookId) throws BookException{
		Book b= null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bookId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				b = new Book();
				b.setBookId(bookId);
				b.setTitle(rset.getString("title"));
				b.setAuthor(rset.getString("author"));
				b.setPublisher(rset.getString("publisher"));
				b.setPublishDate(rset.getDate("publish_date"));
				b.setPrice(rset.getInt("price"));
			}
			
			if(b == null) {
				rollback(conn);
				throw new BookException(bookId+"번의 책에 대한 정보가 없습니다.");
			}
		} catch (Exception e) {
			throw new BookException(e.getMessage());
		} finally {
			close(rset);
			close(pstmt);
		}
		return b;
	}
}

