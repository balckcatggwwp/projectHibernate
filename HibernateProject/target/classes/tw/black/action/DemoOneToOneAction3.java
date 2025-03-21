package tw.black.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Book;
import tw.black.model.BookDetail;
import tw.black.model.BookService;
import tw.black.util.HibernateUtil;

public class DemoOneToOneAction3 {

	public static void main(String[] args) {

		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			//////////////////////////////////////////
			Book book1 = new Book("Treasure Island", "Robert Louis Steveson", 400);
			BookDetail bookDetail = new BookDetail("Vocabbett", "UK");
			BookService bService = new BookService(session);
			bService.inser(book1, bookDetail);
			
			
			List<Book> lists =  bService.selectAll();
			for(Book resultBean :lists) {
				System.out.println(resultBean.getId()+" "+resultBean.getBookname()+" "+resultBean.getAuthor()+" "+resultBean.getPrice());
			}
		
		/////////////////////////////////////////
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
