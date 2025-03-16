package tw.black.model;

import java.util.List;

public interface IHouseDao {
	public House inser(House inserBean) ;
	public House selectById(Integer id);
	public List<House> selectAll();
	public boolean delebyId(Integer id);
	public House updateById(Integer houseid ,String housename);
	
	
	
}
