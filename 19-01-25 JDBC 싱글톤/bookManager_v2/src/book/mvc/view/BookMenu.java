package book.mvc.view;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import book.mvc.controller.BookController;
import book.mvc.model.vo.Book;

public class BookMenu {
	//DI
	private Scanner sc = new Scanner(System.in);
	private BookController bControll = new BookController();
	
	//기본생성자
	public BookMenu() {}
	
	//도서 관리 메뉴창
	public void displayMenu() {
		int no;
		Book b = new Book();
		do {
			System.out.print("*** 도서 관리 프로그램 ***\n\r"
				+ "1. 도서 추가    => BookController 의 insertBook(Book객체)호출\n"
				+ "2. 도서 정보 수정 => BookController 의 updateBook(Book객체) 호출\n"
				+ "3. 도서 삭제 => BookController 의 deleteBook(책아이디) 호출\n"
				+ "4. 도서 아이디로 조회 => BookController 의 searchBook(아이디) 호출\n"
				+ "5. 도서 제목으로 조회 => BookController 의 searchBookTitle(제목) 호출\n"
				+ "6. 도서목록 전체 조회 => BookController 의 selectAll() 호출\n"
				+ "9. 끝내기\n"
				+ "메뉴 선택 :");
			no = sc.nextInt();
			switch(no) {
			case 1 : bControll.insertBook(inputBook()); break;
			case 2 : bControll.updateBook(inputBook(b)); break;
			case 3 : bControll.deleteBook(inputBookId()); break;
			case 4 : displayBook(bControll.searchBook(inputBookId())); break;
			case 5 : displayBook(bControll.searchBookTitle(inputBookTitle())); break;
			case 6 : displayBooks(bControll.selectAll()); break;
			case 9 : System.out.println("종료(y), 취소(n)");
				if(sc.next().toLowerCase().charAt(0) == 'y') {
					return;
				}else {
					break;
				}
			default : System.out.println("입력번호가 없습니다.");
			 			System.out.println("번호를 다시 입력해주세요.");
			}
		}while(true);
	}

	// 새 도서정보 키보드 입력용
	public Book inputBook() {
		Book b = new Book();
		
		System.out.print("책 제목 :");
		sc.nextLine();
		b.setTitle(sc.nextLine());
		System.out.print("저자명 :");
		b.setAuthor(sc.nextLine());
		System.out.print("출판사명 :");
		b.setPublisher(sc.next());
		System.out.print("출판날짜[yyyy-MM-dd] :");
		b.setPublishDate(Date.valueOf(sc.next()));
		System.out.print("가격 :");
		b.setPrice(sc.nextInt());
		
		return b;
	}

	// 수정도서정보 키보드 입력용
	public Book inputBook(Book b) {
		System.out.print("\n변경할 책 번호 :");
		b.setBookId(sc.nextInt());
		System.out.print("변경할 가격 : ");
		b.setPrice(sc.nextInt());
		return b;
	}

	// 검색/삭제할 도서 아이디 입력용
	public int inputBookId() {
		System.out.print("책 아이디 :");
		return sc.nextInt();
	}

	// 도서제목 키보드 입력용
	public String inputBookTitle() {
		System.out.print("도서제목 :");
		return sc.next();
	}

	// 도서목록 출력용
	public void displayBooks(List<Book> list) {
		System.out.println(list.size() + "권의 책이 등록됬습니다.");
		
		for(Book b : list) {
			System.out.println(b);
		}
	}

	// 도서 출력용
	public void displayBook(Book b) {
		System.out.println(b);
	}

	// 에러메세지 출력용
	public void displayError(String message) {
		System.out.println(message);
	}
	
}
