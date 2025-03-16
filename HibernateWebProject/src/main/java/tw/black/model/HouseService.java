package tw.black.model;

import java.util.List;

import org.hibernate.Session;

public class HouseService  implements IHouseService{
	private HouserDao housedao;

	public HouseService(Session session) {
		housedao =new HouserDao(session);
	}
	@Override
	public House inser(House inserBean) {
		return housedao.inser(inserBean);
	}
	@Override
	public House selectById(Integer id) {
		return housedao.selectById(id);
	}
	@Override
	public List<House> selectAll() {
		return housedao.selectAll();
	}
	@Override
	public boolean delebyId(Integer id) {
		return housedao.delebyId(id);
	}
	@Override
	public House updateById(Integer houseid ,String housename) {
		return housedao.updateById(houseid,housename);
	}
}
