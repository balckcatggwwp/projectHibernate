package tw.black.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;



public class BookDao {

	private Session session;

	public BookDao(Session session) {
		this.session = session;
	}
	
	public boolean  inser(Book book , BookDetail bookDetail) {
		
		book.setBookdetail(bookDetail);
		bookDetail.setBook(book);
		session.persist(book);
		return true;
		
		
		
		
	}
	
	
	public Book selectById(Integer id) {
		return session.get(Book.class, id);
		
		
	}
	
	
	public List<Book> selectAll() {
		Query<Book> query = session.createQuery("from Book",Book.class);
		return query.list();
		
	}
	
	
}
