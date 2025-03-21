package tw.black.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Book;
import tw.black.model.BookDetail;
import tw.black.util.HibernateUtil;

public class DemoOneToOneAction2 {

	public static void main(String[] args) {

		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			//////////////////////////////////////////
			Book restltBean = session.get(Book.class,10000);
			if(restltBean!=null) {
				System.out.println(restltBean.getId()+" "+restltBean.getBookname()+" "+restltBean.getAuthor()+" "+restltBean.getPrice());
				BookDetail bookDetail = restltBean.getBookdetail();
				System.out.println(bookDetail.getId()+" "+bookDetail.getPublisher()+" "+ bookDetail.getPublisheraddress());
				
			}else {
				System.out.println("no");
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
