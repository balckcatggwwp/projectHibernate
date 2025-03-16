package tw.black.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Book;
import tw.black.model.BookDetail;
import tw.black.util.HibernateUtil;

public class DemoOneToOneAction {

	public static void main(String[] args) {

			SessionFactory factory= HibernateUtil.getSessionFactory();
			Session session = factory.getCurrentSession();
			try {
				session.beginTransaction();
				Book book = new Book("HerryPitter", "J.K.Rowling", 440);
				BookDetail bookDetail = new BookDetail("Bollmberry", "UK");
				
				book.setBookdetail(bookDetail);
				bookDetail.setBook(book);
				session.persist(book);
				
				
				
				
				session.getTransaction().commit();
			} catch (Exception e) {
				// TODO: handle exception
				session.getTransaction().rollback();
			}finally {
				HibernateUtil.closeSessionFactory();
			}
	}

}
