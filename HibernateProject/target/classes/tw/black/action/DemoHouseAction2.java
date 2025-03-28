package tw.black.action;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.black.model.House;

import tw.black.util.HibernateUtil;

public class DemoHouseAction2 {

	public static void main(String[] args) {

		
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			
			Query<House> query= session.createQuery("from House where houseid =:id and housename=:hname",House.class);
			query.setParameter("id", 1000);
			query.setParameter("hname", "Happy House");
			
			House resultBean =  query.uniqueResult();
			if(resultBean!=null) {
				System.out.println(resultBean.getHouseid()+" "+resultBean.getHousename());
			}else {
				System.out.println("no");
			}
			System.out.println("-----------------------------");
			Query<House> query1= session.createQuery("select h.housename from House h where h.houseid=:id and h.housename=:hname",House.class);
			query1.setParameter("id", 1001);
			query1.setParameter("hname", "Great House");
			House result= query1.getSingleResult();
			
			System.out.println(result.getHouseid()+" "+result.getHousename());
			System.out.println("-----------------------------");
			Query<String> query2= session.createQuery("select h.housename from House h where h.houseid=:id and h.housename=:hname",String.class);
			query2.setParameter("id", 1001);
			query2.setParameter("hname", "Great House");
			List<String> lists = query2.list();
			for(String hbean:lists) {
				System.out.println("HouseName:"+hbean);
			}
			
//			House result= query1.getSingleResult();
			
//			System.out.println(result.getHouseid()+" "+result.getHousename());
			
			
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
