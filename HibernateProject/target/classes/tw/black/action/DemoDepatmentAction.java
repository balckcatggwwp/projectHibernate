package tw.black.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import tw.black.model.Department;

public class DemoDepatmentAction {

	public static void main(String[] args) {
		
		StandardServiceRegistry registry= new StandardServiceRegistryBuilder().configure().build();
		SessionFactory factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		Department dept = new Department();
		dept.setDeptname("Shipping");
		session.persist(dept);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

}
