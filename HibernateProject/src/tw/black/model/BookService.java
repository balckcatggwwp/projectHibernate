package tw.black.model;

import java.util.List;

import org.hibernate.Session;

public class BookService {

	private BookDao bookDao;
	
	
	public BookService (Session session) {
		bookDao = new BookDao(session);
		
		
	}
	
	public boolean  inser(Book book , BookDetail bookDetail) {
		return bookDao.inser(book, bookDetail);
	}
	
	
	public Book selectById(Integer id) {
		return bookDao.selectById(id);
	}
	
	
	public List<Book> selectAll() {
		return bookDao.selectAll();
	}
}
