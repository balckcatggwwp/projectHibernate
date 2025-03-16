package tw.black.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;



public class HouserDao implements IHouseDao {
	
	
	private Session session;
	
	public HouserDao(Session session) {
		this.session = session;
	}
	
	@Override
	public House inser(House inserBean) {
		if(inserBean!=null) {
			session.persist(inserBean);
			return inserBean;
		}
		return null;
	}
	
	@Override
	public House selectById(Integer id) {
		return session.get(House.class,id);
	}
	
	@Override
	public List<House> selectAll() {
		Query<House> query=session.createQuery("from House",House.class);
		return query.list();
	}
	@Override
	public boolean delebyId(Integer id) {
		House delBean = session.get(House.class, id);
		if(delBean!=null) {
			session.remove(delBean);
			return true;
		}
		return false;
	}
	@Override
	public House updateById(Integer houseid ,String housename) {
		House resultBean= session.get(House.class,houseid);
		if (resultBean!=null) {
			resultBean.setHousename(housename);
			
		}
		return resultBean;
	}
}
