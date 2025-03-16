package tw.black.action;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.House;
import tw.black.model.HouseService;
import tw.black.model.IHouseService;
import tw.black.util.HibernateUtil;

public class DemoHouseAction {

	public static void main(String[] args) {

		
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			IHouseService hservice = new HouseService(session);
			List<House> lists = hservice.selectAll();
			Iterator<House> il = lists.iterator();
			while(il.hasNext()) {
				House hBean = il.next();
				System.out.println(hBean.getHouseid()+" "+hBean.getHousename());
				
			}
			System.out.println("----------------------------");
//			House inserBean = new House("Glory House");
//			hservice.inser(inserBean);
			System.out.println("----------------------------");
//			hservice.updateById(1009, "War house");
//			hservice.delebyId(1009);
			House res = hservice.selectById(1000);
			if(res!=null) {
				System.out.println(res.getHouseid()+" "+res.getHousename());
				
			}else {
				System.out.println("f");
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			HibernateUtil.closeSessionFactory();
		}
	}

}
