package tw.black.action;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import tw.black.model.Department;
import tw.black.util.HibernateUtil;

public class DempDepartmentAction3 {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
//			Department deptBean = session.get(Department.class,6);
//			if (deptBean!=null) {
//				System.out.println(deptBean.getDeptid()+" "+deptBean.getDeptname());
//				//查詢單筆
//				
//			}else {
//				System.out.println("Nio Result");
//			}
//			System.out.println("-------------------------");
//			Query<Department> query= session.createQuery("from Department",Department.class);
//			
//			List<Department> lists = query.list();
//			
//			for(Department dept:lists) {
//				//查詢全部
//				System.out.println(dept.getDeptid()+" "+dept.getDeptname());
//			}
			
//			Department deleBean = new Department();
//			deleBean.setDeptid(5);
//			
//			deleBean.setDeptname("QA");
//			session.remove(deleBean);  //刪除
			
//			Department inserBean = new Department("QA");
//			session.persist(inserBean);
			
//			Department updateBean = new Department(6,"Secretary");
//			session.update(updateBean);
//			session.flush(); //更新
			
			
			Department resultBean= session.get(Department.class,6);
			if (resultBean!= null) {
				resultBean.setDeptname("QA");
				
	 		}else {
	 			System.out.println("沒有這id");
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
