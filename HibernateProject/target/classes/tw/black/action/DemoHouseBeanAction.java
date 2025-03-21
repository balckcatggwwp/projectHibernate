package tw.black.action;

import java.sql.RowId;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tw.black.model.HouseBean;

public class DemoHouseBeanAction {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		Session session =factory.openSession();
		Transaction ts= session.beginTransaction();
		HouseBean inserBean = new HouseBean("Wonderful House");
		//session.persist(inserBean);
		session.save(inserBean);  //新增
		
		ts.commit();
		session.close();
		factory.close();
	}

}
