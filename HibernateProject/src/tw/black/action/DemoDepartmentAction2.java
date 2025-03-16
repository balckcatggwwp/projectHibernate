package tw.black.action;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Department;
import tw.black.util.HibernateUtil;

public class DemoDepartmentAction2 {
	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		Department dept2 = new Department();
		dept2.setDeptname("QA");
		session.persist(dept2);
		session.getTransaction().commit();
		
		session.close();
		HibernateUtil.closeSessionFactory();
	}
}
