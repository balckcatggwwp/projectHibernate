package tw.black.action;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import tw.black.model.Stock;
import tw.black.model.StockTransaction;
import tw.black.util.HibernateUtil;

public class DemoOneToManyAction2 {

	public static void main(String[] args) {

		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {	
			session.beginTransaction();
			//////////////////////////////////////////
			Stock resultBean= session.get(Stock.class, 2);
			if(resultBean!=null) {
				System.out.println(resultBean.getStockid()+" "+resultBean.getStockname()+" "+resultBean.getStockcode());
				System.out.println("----------------------------------------");
				List<StockTransaction> trans = resultBean.getStockTransactions();
				for(StockTransaction stockTrans:trans) {
					System.out.println(stockTrans.getStocktransid()+" "+stockTrans.getTradevolume()+" "+stockTrans.getStockid());
				}
			}else {
				System.out.println("f");
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
