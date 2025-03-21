package tw.black.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.HouseBean;
import tw.black.util.HibernateUtil;

public class DemoHouseBeanAction3 {

	public static void main(String[] args) {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			
			session.beginTransaction();
			System.out.println("Transaction Begin");
			
			HouseBean hBean1 = new HouseBean("Sweet house");
			session.persist(hBean1);
			
			
			
			session.getTransaction().commit();
			System.out.println("Transaction commit");
			
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
			System.out.println("Session Closed");
			HibernateUtil.closeSessionFactory();
		}
	}

}
