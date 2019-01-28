package book.mvc.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable{
	//직렬화처리
	private static final long SerialVersionUID = 911204L;
	
	//Field
	private int bookId;             //책번호
	private String title;			   //책제목
	private String author;		   //저자명
	private String publisher;	   //출판사
	private Date publishDate;    //출판날짜
	private int price;				   //가격
	
	//생성자
	public Book() {}

	public Book(int bookId, String title, String author, String publisher, Date publishDate, int price) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publishDate = publishDate;
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public static long getSerialversionuid() {
		return SerialVersionUID;
	}

	@Override
	public String toString() {
		return bookId + ", " + title + ", " + author + ", " + publisher + ", " + publishDate + ", " + price;
	}
	
}
