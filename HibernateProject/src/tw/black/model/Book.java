package tw.black.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
@Entity @Table(name="book")
public class Book {
	@Id@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="BOOKNAME")
	private String bookname;
	
	@Column(name="AUTHOR")
	private String author;
	@Column(name="PRICE")
	private int price;
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "book",cascade = CascadeType.ALL)
	private BookDetail bookdetail;
	
	
	public Book() {
		
		
		
	}


	public Book( String bookname, String author, int price) {
		super();
		
		this.bookname = bookname;
		this.author = author;
		this.price = price;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getBookname() {
		return bookname;
	}


	public void setBookname(String bookname) {
		this.bookname = bookname;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public BookDetail getBookdetail() {
		return bookdetail;
	}


	public void setBookdetail(BookDetail bookdetail) {
		this.bookdetail = bookdetail;
	}
	

}
