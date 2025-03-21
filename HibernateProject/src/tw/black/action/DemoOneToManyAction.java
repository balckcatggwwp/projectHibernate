package tw.black.action;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import tw.black.model.Book;
import tw.black.model.BookDetail;
import tw.black.model.BookService;
import tw.black.model.Stock;
import tw.black.model.StockTransaction;
import tw.black.util.HibernateUtil;

public class DemoOneToManyAction {

	public static void main(String[] args) {

		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			//////////////////////////////////////////
			Stock stock1 =new Stock("TSMC", "2330");
			StockTransaction trans1 = new StockTransaction(25000);
			StockTransaction trans2 = new StockTransaction(5000);
			StockTransaction trans3 = new StockTransaction(9500);
			
			
			
			trans1.setStock(stock1);
			trans2.setStock(stock1);
			trans3.setStock(stock1);
			
			LinkedList<StockTransaction> stockTrans = new LinkedList<StockTransaction>();
			stockTrans.add(trans1);
			stockTrans.add(trans2);
			stockTrans.add(trans3);
			stock1.setStockTransactions(stockTrans);
			session.persist(stock1);
			
			
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
