package tw.black.action;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Book;
import tw.black.model.BookDetail;
import tw.black.model.BookService;
import tw.black.model.Game;
import tw.black.model.Role;
import tw.black.model.Stock;
import tw.black.model.StockTransaction;
import tw.black.util.HibernateUtil;

public class DemoManyToManyAction {

	public static void main(String[] args) {

		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			//////////////////////////////////////////
			Game game1 =new Game("Diablo");
			
			
			Role role1=new Role("warrior","sword");
			Role role2=new Role("magician","fireball");
			Role role3=new Role("goblin","axe");
			HashSet<Role> roles = new HashSet<Role>();
			roles.add(role1);
			roles.add(role2);
			roles.add(role3);
			
			game1.setRoles(roles);
			session.persist(game1);
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
